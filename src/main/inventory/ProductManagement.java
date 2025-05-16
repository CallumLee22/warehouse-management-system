package main.inventory;

import main.exceptions.ProductNotFoundException;
import main.suppliers.SupplierManagement;

import java.util.HashMap;

public class ProductManagement {
    private final HashMap<Integer, Product> products = new HashMap<>();
    private int nextId = 1;
    private final SupplierManagement supplierManagement;

    public ProductManagement(SupplierManagement supplierManagement) {
        this.supplierManagement = supplierManagement;
    }

    public void addProduct(String name, double buyPrice, double sellPrice, int initialStock, int supplierId) {
        Product newProduct = new Product(nextId, supplierId, name, buyPrice, sellPrice, initialStock);
        products.put(nextId, newProduct);
        supplierManagement.getSupplierById(supplierId).addAvailableProduct(newProduct);
        nextId++;
    }

    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    public void removeProduct(int id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when removing");
        }
        products.remove(id);
    }

    public void updateName(int id, String newName) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(newName);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating name");
        }
    }

    public void updateBuyPrice(int id, double newPrice) {
        Product product = products.get(id);
        if (product != null) {
            product.setBuyPrice(newPrice);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating price");
        }
    }

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
