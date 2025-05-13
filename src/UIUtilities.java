public class UIUtilities {
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

    public static void displayProductsToBuy(SupplierManagement supplierManagement) {
        if (supplierManagement.getSuppliers().isEmpty()) {
            System.out.println("No suppliers found.");
            return;
        }

        for (Integer key : supplierManagement.getSuppliers().keySet()) {
            System.out.println("###########################");
            System.out.println("[" + key + "] ");
            System.out.println("---------------------------");
            System.out.println("Name: " + supplierManagement.getSuppliers().get(key).getName());
            System.out.println("Phone Number: " + supplierManagement.getSuppliers().get(key).getPhoneNumber());
            System.out.println("---------------------------");
            System.out.println("Products Available:");
            System.out.println("---------------------------");
            for (Product product : supplierManagement.getSuppliers().get(key).getAvailableProducts()) {
                System.out.println("[" + product.getId() + "] ");
                System.out.println("Name: " + product.getName());
                System.out.println("Buy Price: £" + product.getBuyPrice());
                System.out.println("---------------------------");
            }
        }
    }
}
