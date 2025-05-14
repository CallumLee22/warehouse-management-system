import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

public class BuyOrderManagementTests {
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);

    @Before
    public void setUp() {
        supplierManagement.addSupplier("Test Supplier", "1234567890");
        supplierManagement.addSupplier("Another Supplier", "0987654321");
        productManagement.addProduct("Test Product", 12.50, 20.90, 1, 1);
        productManagement.addProduct("Another Product", 15.00, 25.00, 2, 2);
    }

    @Test
    public void testCreateBuyOrder_ValidProductId() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        buyOrderManagement.createOrder(productsForOrder);

        assertEquals(1, buyOrderManagement.getOrders().size());
        assertEquals(5, buyOrderManagement.getOrders().get(1).getProducts().getFirst().quantity());
        assertEquals("Test Product", buyOrderManagement.getOrders().get(1).getProducts().getFirst().product().getName());
        assertEquals(productManagement.getProducts().get(1).getBuyPrice() * 5, buyOrderManagement.getOrders().get(1).getTotalPrice(), 0.01);
    }

    @Test
    public void testCreateBuyOrder_EmptyProductList() {
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            buyOrderManagement.createOrder(productsForOrder);
        });

        assertEquals("Order cannot be empty.", exception.getMessage());
        assertEquals(0, buyOrderManagement.getOrders().size());
    }

    @Test
    public void testCreateBuyOrder_InvalidProductId() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(999), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            buyOrderManagement.createOrder(productsForOrder);
        });

        assertEquals("Invalid product in order", exception.getMessage());
        assertEquals(0, buyOrderManagement.getOrders().size());
    }

    @Test
    public void testUpdateStock_ValidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        buyOrderManagement.createOrder(productsForOrder);

        buyOrderManagement.acceptDelivery(1);
        assertEquals(initialStock + 5, productManagement.getProducts().get(1).getStock());
    }

    @Test
    public void testUpdateStock_InvalidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(999), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            buyOrderManagement.createOrder(productsForOrder);
        });

        assertEquals("Invalid product in order", exception.getMessage());
        assertEquals(initialStock, productManagement.getProducts().get(1).getStock());
    }

    @Test
    public void testAcceptDelivery_OrderNotFound() {
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            buyOrderManagement.acceptDelivery(999);
        });

        assertEquals("Order with ID 999 was not found when accepting delivery", exception.getMessage());
    }

    @Test
    public void testOrderStatusChange() throws InterruptedException {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        buyOrderManagement.createOrder(productsForOrder);

        assertEquals(BuyOrderStatus.PROCESSING, buyOrderManagement.getOrders().get(1).getStatus());

        Thread.sleep(6000);
        assertEquals(BuyOrderStatus.SHIPPED, buyOrderManagement.getOrders().get(1).getStatus());

        Thread.sleep(6000);
        assertEquals(BuyOrderStatus.READY_FOR_DELIVERY, buyOrderManagement.getOrders().get(1).getStatus());
    }
}
