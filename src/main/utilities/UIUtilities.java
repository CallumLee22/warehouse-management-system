package main.utilities;

import main.inventory.Product;
import main.inventory.ProductManagement;
import main.suppliers.SupplierManagement;

/**
 * UIUtilities class provides methods to display information about suppliers and products
 * that are reused in various parts of the UI.
 * It formats the output for better readability in the console.
 */
public class UIUtilities {
    /**
     * Displays a list of suppliers in a formatted manner.
     * If no suppliers are found, it informs the user.
     *
     * @param supplierManagement The SupplierManagement instance containing suppliers.
     */
    public static void displaySuppliers(SupplierManagement supplierManagement) {
        if (supplierManagement.getSuppliers().isEmpty()) {
            System.out.println("No suppliers found.");
            return;
        }

        for (Integer key : supplierManagement.getSuppliers().keySet()) {
            System.out
                    .println("[" + key + "] " + supplierManagement.getSupplierById(key).getName()
                            + " - " + supplierManagement.getSupplierById(key).getPhoneNumber());
        }
    }

    /**
     * Displays a list of products in a formatted manner.
     * If no products are found, it informs the user.
     *
     * @param productManagement The ProductManagement instance containing products.
     */
    public static void displayProducts(ProductManagement productManagement) {
        if (productManagement.getProducts().isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Integer key : productManagement.getProducts().keySet()) {
            System.out.println("[" + key + "] " + productManagement.getProducts().get(key).getName() + " - "
                    + productManagement.getProducts().get(key).getStock() + " in stock");
        }
    }

    /**
     * Displays a list of products available for sale in a detailed format.
     * If no products are found, it informs the user.
     *
     * @param productManagement The ProductManagement instance containing products.
     */
    public static void displayProductsForSale(ProductManagement productManagement) {
        if (productManagement.getProducts().isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Integer key : productManagement.getProducts().keySet()) {
            System.out.println("[" + key + "] ");
            System.out.println("-------------------");
            System.out.println("Name: " + productManagement.getProducts().get(key).getName());
            System.out.println("Stock: " + productManagement.getProducts().get(key).getStock());
            System.out.println("Sell Price: £" + productManagement.getProducts().get(key).getSellPrice());
            System.out.println("-------------------");
        }
    }

    /**
     * Displays a list of products available for purchase from a specific supplier.
     * If no products are available, it informs the user.
     *
     * @param supplierManagement The SupplierManagement instance containing suppliers.
     * @param id                 The ID of the supplier whose products are to be displayed.
     */
    public static void displayProductsToBuyFromSupplier(SupplierManagement supplierManagement, int id) {
        if (supplierManagement.getSuppliers().get(id).getAvailableProducts().isEmpty()) {
            System.out.println("No available products found for supplier with ID " + id);
            return;
        }

        System.out.println("Products Available:");
        System.out.println("---------------------------");
        for (Product product : supplierManagement.getSuppliers().get(id).getAvailableProducts()) {
            System.out.println("[" + product.getId() + "] ");
            System.out.println("Name: " + product.getName());
            System.out.println("Buy Price: £" + product.getBuyPrice());
            System.out.println("---------------------------");
        }
    }
}
