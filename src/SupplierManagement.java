import java.util.ArrayList;

public class SupplierManagement {
    private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

    public static void addSupplier(String name, String phoneNumber) {
        suppliers.add(new Supplier(0, name, phoneNumber, null));
    }

    public static ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
}
