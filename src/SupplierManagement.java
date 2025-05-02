import java.util.HashMap;

public class SupplierManagement {
    private HashMap<Integer, Supplier> suppliers = new HashMap<>();
    private int nextId = 1;

    public SupplierManagement() {
        
    }

    public void addSupplier(String name, String phoneNumber) {
        suppliers.put(nextId, new Supplier(name, phoneNumber, null));
        nextId++;
    }

    public HashMap<Integer, Supplier> getSuppliers() {
        return suppliers;
    }

    public void removeSupplier(int id) {
        suppliers.remove(id);
    }

    public void updatePhoneNumber(int id, String phoneNumber) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.setPhoneNumber(phoneNumber);
        }
        else {
            throw new SupplierNotFoundException("Supplier with ID " + id + " was not found when updating phone number");
        }
    }
}
