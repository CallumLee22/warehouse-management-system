import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class BuyOrderManagementTests {
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);

    @Before
    public void setUp() {
        productManagement.addProduct("Test Product", 12.50, 20.90, 1, 1);
        productManagement.addProduct("Another Product", 15.00, 25.00, 2, 2);
    }

    @Test
    public void testCreateBuyOrder() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        buyOrderManagement.createOrder(productsForOrder);

        assertEquals(1, buyOrderManagement.getOrders().size());
        assertEquals(5, buyOrderManagement.getOrders().getFirst().getProducts().getFirst().quantity());
        assertEquals("Test Product", buyOrderManagement.getOrders().getFirst().getProducts().getFirst().product().getName());
        assertEquals(productManagement.getProducts().get(1).getBuyPrice() * 5, buyOrderManagement.getOrders().getFirst().getTotalPrice(), 0.01);
    }

    @Test
    public void testUpdateStock_ValidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        buyOrderManagement.createOrder(productsForOrder);

        assertEquals(initialStock + 5, productManagement.getProducts().get(1).getStock());
    }
}
