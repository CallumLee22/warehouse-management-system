import org.junit.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SupplierManagementTests {
    SupplierManagement supplierManagement = new SupplierManagement();

    @Test
    public void testAddSupplier_ValidPhoneNumber() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        HashMap<Integer, Supplier> suppliers = supplierManagement.getSuppliers();

        assertEquals(1, suppliers.size());
        assertEquals("Supplier1", suppliers.get(1).getName());
    }

    @Test
    public void testAddSupplier_InvalidPhoneNumber() {
        supplierManagement.addSupplier("Supplier1", "invalid");
        HashMap<Integer, Supplier> suppliers = supplierManagement.getSuppliers();

        assertEquals(0, suppliers.size());
    }

    @Test
    public void testRemoveSupplier_ValidId() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        supplierManagement.removeSupplier(1);

        assertTrue(supplierManagement.getSuppliers().isEmpty());
    }

    @Test
    public void testRemoveSupplier_InvalidId() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        supplierManagement.removeSupplier(2);

        assertEquals(1, supplierManagement.getSuppliers().size());
    }

    @Test
    public void testUpdateName_ValidId() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        supplierManagement.updateName(1, "UpdatedSupplier");

        assertEquals("UpdatedSupplier", supplierManagement.getSuppliers().get(1).getName());
    }

    @Test
    public void testUpdateName_InvalidId() {
        Exception exception = assertThrows(SupplierNotFoundException.class, () -> {
            supplierManagement.updateName(1, "UpdatedSupplier");
        });

        assertEquals("Supplier with ID 1 was not found when updating name", exception.getMessage());
    }

    @Test
    public void testUpdatePhoneNumber_ValidId() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        supplierManagement.updatePhoneNumber(1, "0987654321");

        assertEquals("0987654321", supplierManagement.getSuppliers().get(1).getPhoneNumber());
    }

    @Test
    public void testUpdatePhoneNumber_InvalidId() {
        Exception exception = assertThrows(SupplierNotFoundException.class, () -> {
            supplierManagement.updatePhoneNumber(1, "0987654321");
        });

        assertEquals("Supplier with ID 1 was not found when updating phone number", exception.getMessage());
    }
}
