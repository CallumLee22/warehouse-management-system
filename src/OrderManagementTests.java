import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class OrderManagementTests {
    private final ProductManagement productManagement = new ProductManagement();
    private final OrderManagement orderManagement = new OrderManagement(productManagement);

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

        orderManagement.createBuyOrder(productsForOrder);

        assertEquals(1, orderManagement.getBuyOrders().size());
    }

    @Test
    public void testUpdateStock_ValidProductId() {
        int initialStock = productManagement.getProducts().get(1).getStock();

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);

        orderManagement.createBuyOrder(productsForOrder);

        assertEquals(initialStock + 5, productManagement.getProducts().get(1).getStock());
    }
}
