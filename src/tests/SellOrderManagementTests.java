package tests;

import main.exceptions.ProductNotFoundException;
import main.inventory.ProductManagement;
import main.orders.OrderProductEntry;
import main.orders.SellOrderManagement;
import main.suppliers.SupplierManagement;
import org.junit.*;

import java.util.ArrayList;

public class SellOrderManagementTests {
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final SellOrderManagement sellOrderManagement = new SellOrderManagement(productManagement);

    @Before
    public void setUp() {
        supplierManagement.addSupplier("Test Supplier", "1234567890");
        supplierManagement.addSupplier("Another Supplier", "0987654321");
        productManagement.addProduct("Test Product", 12.50, 20.90, 100, 1);
        productManagement.addProduct("Another Product", 15.00, 25.00, 200, 2);
    }

    @Test
    public void testCreateSellOrder_ValidProductId() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        sellOrderManagement.createOrder(productsForOrder);
        Assert.assertEquals(1, sellOrderManagement.getOrders().size());
        Assert.assertEquals(5, sellOrderManagement.getOrders().get(1).getProducts().getFirst().quantity());
        Assert.assertEquals("Test Product", sellOrderManagement.getOrders().get(1).getProducts().getFirst().product().getName());
        Assert.assertEquals(productManagement.getProducts().get(1).getSellPrice() * 5, sellOrderManagement.getOrders().get(1).getTotalPrice(), 0.01);
    }

    @Test
    public void testCreateSellOrder_InvalidProductId() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(999), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = Assert.assertThrows(ProductNotFoundException.class, () -> {
            sellOrderManagement.createOrder(productsForOrder);
        });

        Assert.assertEquals("Invalid product in order", exception.getMessage());
        Assert.assertEquals(0, sellOrderManagement.getOrders().size());
    }

    @Test
    public void testCreateSellOrder_EmptyProductList() {
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            sellOrderManagement.createOrder(productsForOrder);
        });

        Assert.assertEquals("Order cannot be empty.", exception.getMessage());
        Assert.assertEquals(0, sellOrderManagement.getOrders().size());
    }

    @Test
    public void testCreateSellOrder_NegativeQuantity() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), -5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            sellOrderManagement.createOrder(productsForOrder);
        });

        Assert.assertEquals("Invalid quantity in order", exception.getMessage());
        Assert.assertEquals(0, sellOrderManagement.getOrders().size());
    }

    @Test
    public void testCreateSellOrder_InsufficientStock() {
        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 500);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            sellOrderManagement.createOrder(productsForOrder);
        });

        Assert.assertEquals("Not enough stock for product Test Product", exception.getMessage());
        Assert.assertEquals(0, sellOrderManagement.getOrders().size());
    }

    @Test
    public void testUpdateStock_ValidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        sellOrderManagement.createOrder(productsForOrder);
        Assert.assertEquals(initialStock - 5, productManagement.getProducts().get(1).getStock());
    }

    @Test
    public void testUpdateStock_InvalidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(999), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        Exception exception = Assert.assertThrows(ProductNotFoundException.class, () -> {
            sellOrderManagement.createOrder(productsForOrder);
        });

        Assert.assertEquals("Invalid product in order", exception.getMessage());
        Assert.assertEquals(initialStock, productManagement.getProducts().get(1).getStock());
    }
}
