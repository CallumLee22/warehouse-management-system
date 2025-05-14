import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ProductManagementTests {
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);

    @Before
    public void setUp() {
        supplierManagement.addSupplier("Test Supplier", "1234567890");
    }

    @Test
    public void testAddProduct() {
        productManagement.addProduct("Test product", 12.50, 15.10, 55, 1);

        HashMap<Integer, Product> products = productManagement.getProducts();

        assertEquals(1, products.size());
        assertEquals("Test product", products.get(1).getName());
    }

    @Test
    public void testRemoveProduct() {
        productManagement.addProduct("Test product", 12.50, 15.10, 55, 1);
        productManagement.removeProduct(1);

        HashMap<Integer, Product> products = productManagement.getProducts();

        assertEquals(0, products.size());
    }

    @Test
    public void testUpdateProductName_validId() {
        productManagement.addProduct("Test product", 12.50, 15.76, 55, 1);
        productManagement.updateName(1, "Updated product");

        HashMap<Integer, Product> products = productManagement.getProducts();

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
    public void testUpdateProductSellPrice_ValidId() {
        productManagement.addProduct("Test product", 12.50, 15.25, 55, 1);
        productManagement.updateSellPrice(1, 15.00);

        HashMap<Integer, Product> products = productManagement.getProducts();

        assertEquals(15.00, products.get(1).getSellPrice(), 0.01);
    }

    @Test
    public void testUpdateProductSellPrice_InvalidId() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productManagement.updateSellPrice(1, 20.08);
        });

        assertEquals("Inventory product with ID 1 was not found when updating price", exception.getMessage());
    }
}
