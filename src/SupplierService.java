import java.util.ArrayList;

public class SupplierService {

    public static void addSupplier(String name, String phoneNumber) {
        if (Utilities.isValidPhoneNumber(phoneNumber)) {
            SupplierManagement.addSupplier(name, phoneNumber);
        } else {
            System.out.println("Invalid phone number format.");
        }
    }

    public static ArrayList<Supplier> getSuppliers() {
        return SupplierManagement.getSuppliers();
    }

}
