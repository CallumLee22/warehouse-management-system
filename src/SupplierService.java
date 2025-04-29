import java.util.ArrayList;

public class SupplierService {
    private static SupplierManagement supplierManagement = new SupplierManagement();

    public static void addSupplier(String name, String phoneNumber) {
        if (Utilities.isValidPhoneNumber(phoneNumber)) {
            supplierManagement.addSupplier(name, phoneNumber);
        } else {
            System.out.println("Invalid phone number format.");
        }
    }

    public static ArrayList<Supplier> getSuppliers() {
        return supplierManagement.getSuppliers();
    }

    public static void removeSupplier(int id) {
        if (id < 0 || id >= supplierManagement.getSuppliers().size()) {
            System.out.println("Invalid supplier ID.");
            return;
        }
        supplierManagement.removeSupplier(id);
    }
}
