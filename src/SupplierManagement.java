import java.util.HashMap;

public class SupplierManagement {
    private final HashMap<Integer, Supplier> suppliers = new HashMap<>();
    private int nextId = 1;

    public SupplierManagement() {

    }

    public void addSupplier(String name, String phoneNumber) {
        if (Utilities.isValidPhoneNumber(phoneNumber)) {
            suppliers.put(nextId, new Supplier(nextId, name, phoneNumber));
            nextId++;
        } else {
            System.out.println("Invalid phone number format.");
        }
    }

    public HashMap<Integer, Supplier> getSuppliers() {
        return suppliers;
    }

    public void removeSupplier(int id) {
        if (!suppliers.containsKey(id)) {
            System.out.println("Invalid supplier ID.");
            return;
        }
        suppliers.remove(id);
    }

    public void updateName(int id, String newName) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.setName(newName);
        } else {
            throw new SupplierNotFoundException(
                    "Supplier with ID " + id + " was not found when updating name");
        }
    }

    public void updatePhoneNumber(int id, String phoneNumber) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.setPhoneNumber(phoneNumber);
        } else {
            throw new SupplierNotFoundException(
                    "Supplier with ID " + id + " was not found when updating phone number");
        }
    }

    public Supplier getSupplierById(int id) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            return supplier;
        } else {
            throw new SupplierNotFoundException("Supplier with ID " + id + " was not found when getting supplier");
        }
    }
}
