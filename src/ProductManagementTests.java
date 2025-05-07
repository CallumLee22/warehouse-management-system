import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ProductManagementTests {
    ProductManagement productManagement = new ProductManagement();

    @Test
    public void testAddProduct() {
        productManagement.addProduct("Test product", 12.50, 10, 55);

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProductHashMap();

        assertEquals(1, products.size());
        assertEquals("Test product", products.get(1).getName());
    }

    @Test
    public void testRemoveProduct() {
        productManagement.addProduct("Test product", 12.50, 10, 55);
        productManagement.removeProduct(1);

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProductHashMap();

        assertEquals(0, products.size());
    }
}
