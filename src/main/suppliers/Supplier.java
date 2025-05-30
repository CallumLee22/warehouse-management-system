package main.suppliers;

import main.inventory.Product;

import java.util.ArrayList;

/**
 * Represents a supplier in the system.
 * Each supplier has an ID, name, phone number, and a list of products they supply.
 */
public class Supplier {
    private final int id;
    private String name;
    private String phoneNumber;
    private final ArrayList<Product> productsAvailable = new ArrayList<>();

    /**
     * Constructs a Supplier with the specified ID, name, and phone number.
     *
     * @param id          the unique identifier for the supplier
     * @param name        the name of the supplier
     * @param phoneNumber the contact phone number of the supplier
     */
    public Supplier(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the supplier's ID.
     *
     * @return The ID of the supplier
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the name of the supplier.
     *
     * @return The name of the supplier
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the phone number of the supplier.
     *
     * @return The phone number of the supplier
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns a list of products available from the supplier.
     *
     * @return A list of products supplied by the supplier
     */
    public ArrayList<Product> getAvailableProducts() {
        return this.productsAvailable;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param name  New name for the supplier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the phone number of the supplier.
     *
     * @param phoneNumber   New phone number for the supplier
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Adds a product to the list of products available from the supplier.
     *
     * @param product   Product to be added to the supplier's offerings
     */
    public void addAvailableProduct(Product product) {
        this.productsAvailable.add(product);
    }
}
