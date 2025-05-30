package main.inventory;

import main.exceptions.ProductNotFoundException;
import main.suppliers.SupplierManagement;

import java.util.HashMap;

/**
 * Manages the products in the inventory system.
 * Allows adding, removing, and updating product details.
 */
public class ProductManagement {
    private final HashMap<Integer, Product> products = new HashMap<>();
    private int nextId = 1;
    private final SupplierManagement supplierManagement;

    /**
     * Constructs a ProductManagement with the specified SupplierManagement.
     *
     * @param supplierManagement    The management system for suppliers
     */
    public ProductManagement(SupplierManagement supplierManagement) {
        this.supplierManagement = supplierManagement;
    }

    /**
     * Adds a new product to the inventory.
     *
     * @param name          The name of the product
     * @param buyPrice      The buying price of the product
     * @param sellPrice     The selling price of the product
     * @param initialStock  The initial stock quantity of the product
     * @param supplierId    The ID of the supplier providing this product
     */
    public void addProduct(String name, double buyPrice, double sellPrice, int initialStock, int supplierId) {
        Product newProduct = new Product(nextId, name, buyPrice, sellPrice, initialStock);
        products.put(nextId, newProduct);
        supplierManagement.getSupplierById(supplierId).addAvailableProduct(newProduct);
        nextId++;
    }

    /**
     * Retrieves all products in the inventory
     * @return A HashMap of products
     */
    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    /**
     * Removes a product from the inventory by its ID.
     *
     * @param id    The ID of the product to remove
     */
    public void removeProduct(int id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when removing");
        }
        products.remove(id);
    }

    /**
     * Updates the name of a product in the inventory.
     *
     * @param id    The ID of the product to update
     * @param newName   The new name for the product
     */
    public void updateName(int id, String newName) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(newName);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating name");
        }
    }

    /**
     * Updates the buy price of a product in the inventory.
     *
     * @param id    The ID of the product to update
     * @param newPrice   The new buy price for the product
     */
    public void updateBuyPrice(int id, double newPrice) {
        Product product = products.get(id);
        if (product != null) {
            product.setBuyPrice(newPrice);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating price");
        }
    }

    /**
     * Updates the sell price of a product in the inventory.
     *
     * @param id    The ID of the product to update
     * @param newPrice   The new sell price for the product
     */
    public void updateSellPrice(int id, double newPrice) {
        Product product = products.get(id);
        if (product != null) {
            product.setSellPrice(newPrice);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating price");
        }
    }
}
