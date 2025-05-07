import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ProductManagementTests {
    ProductManagement productManagement = new ProductManagement();

    @Test
    public void testAddProduct() {
        productManagement.addProduct("Test product", 12.50, 55);

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProducts();

        assertEquals(1, products.size());
        assertEquals("Test product", products.get(1).getName());
    }

    @Test
    public void testRemoveProduct() {
        productManagement.addProduct("Test product", 12.50, 55);
        productManagement.removeProduct(1);

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProducts();

        assertEquals(0, products.size());
    }

    @Test
    public void testUpdateProductName_validId() {
        productManagement.addProduct("Test product", 12.50, 55);
        productManagement.updateName(1, "Updated product");

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProducts();

        assertEquals("Updated product", products.get(1).getName());
    }

    @Test
    public void testUpdateProductName_InvalidId() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productManagement.updateName(1, "UpdatedProduct");
        });

        assertEquals("Inventory product with ID 1 was not found when updating name", exception.getMessage());
    }

    @Test
    public void testUpdateProductPrice_ValidId() {
        productManagement.addProduct("Test product", 12.50, 55);
        productManagement.updatePrice(1, 15.00);

        HashMap<Integer, InventoryProduct> products = productManagement.getInventoryProducts();

        assertEquals(15.00, products.get(1).getPrice(), 0.01);
    }

    @Test
    public void testUpdateProductPrice_InvalidId() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productManagement.updatePrice(1, 20.08);
        });

        assertEquals("Inventory product with ID 1 was not found when updating price", exception.getMessage());
    }
}
