import java.util.HashMap;

public class SupplierService {
    private static SupplierManagement supplierManagement = new SupplierManagement();

    public static void addSupplier(String name, String phoneNumber) {
        if (Utilities.isValidPhoneNumber(phoneNumber)) {
            supplierManagement.addSupplier(name, phoneNumber);
        } else {
            System.out.println("Invalid phone number format.");
        }
    }

    public static HashMap<Integer, Supplier> getSuppliers() {
        return supplierManagement.getSuppliers();
    }

    public static void removeSupplier(int id) {
        if (!supplierManagement.getSuppliers().containsKey(id)) {
            System.out.println("Invalid supplier ID.");
            return;
        }
        supplierManagement.removeSupplier(id);
    }

    public static void updateSupplierPhoneNumber(int id, String phoneNumber) {
        supplierManagement.updatePhoneNumber(id, phoneNumber);
    }
}
