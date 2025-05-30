package main.suppliers;

import main.exceptions.SupplierNotFoundException;
import main.utilities.Utilities;

import java.util.HashMap;

/**
 * SupplierManagement class handles the management of suppliers.
 * It allows adding, removing, updating, and retrieving suppliers.
 */
public class SupplierManagement {
    private final HashMap<Integer, Supplier> suppliers = new HashMap<>();
    private int nextId = 1;

    /**
     * Constructor for SupplierManagement.
     */
    public SupplierManagement() {

    }

    /**
     * Adds a new supplier to the management system.
     *
     * @param name        The name of the supplier.
     * @param phoneNumber The phone number of the supplier.
     */
    public void addSupplier(String name, String phoneNumber) {
        if (Utilities.isValidPhoneNumber(phoneNumber)) {
            suppliers.put(nextId, new Supplier(nextId, name, phoneNumber));
            nextId++;
        } else {
            System.out.println("Invalid phone number format.");
        }
    }

    /**
     * Retrieves all suppliers.
     *
     * @return A HashMap containing all suppliers.
     */
    public HashMap<Integer, Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Removes a supplier from the system.
     *
     * @param id The ID of the supplier to remove.
     */
    public void removeSupplier(int id) {
        if (!suppliers.containsKey(id)) {
            System.out.println("Invalid supplier ID.");
            return;
        }
        suppliers.remove(id);
    }

    /**
     * Updates the name of a supplier.
     *
     * @param id       The ID of the supplier to update.
     * @param newName  The new name for the supplier.
     */
    public void updateName(int id, String newName) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.setName(newName);
        } else {
            throw new SupplierNotFoundException(
                    "Supplier with ID " + id + " was not found when updating name");
        }
    }

    /**
     * Updates the phone number of a supplier.
     *
     * @param id          The ID of the supplier to update.
     * @param phoneNumber The new phone number for the supplier.
     */
    public void updatePhoneNumber(int id, String phoneNumber) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.setPhoneNumber(phoneNumber);
        } else {
            throw new SupplierNotFoundException(
                    "Supplier with ID " + id + " was not found when updating phone number");
        }
    }

    /**
     * Retrieves a supplier by its ID.
     *
     * @param id The ID of the supplier to retrieve.
     * @return The Supplier object if found.
     */
    public Supplier getSupplierById(int id) {
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            return supplier;
        } else {
            throw new SupplierNotFoundException("Supplier with ID " + id + " was not found when getting supplier");
        }
    }
}
