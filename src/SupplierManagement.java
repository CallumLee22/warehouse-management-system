import java.util.ArrayList;

public class SupplierManagement {
    private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    private int nextId = 1;

    public SupplierManagement() {
        
    }

    public void addSupplier(String name, String phoneNumber) {
        suppliers.add(new Supplier(nextId, name, phoneNumber, null));
        nextId++;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public void removeSupplier(int id) {
        suppliers.removeIf(supplier -> supplier.getId() == id);
    }
}
