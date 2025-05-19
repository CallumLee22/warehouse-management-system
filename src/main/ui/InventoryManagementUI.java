package main.ui;

import main.exceptions.ProductNotFoundException;
import main.exceptions.SupplierNotFoundException;
import main.inventory.ProductManagement;
import main.orders.BuyOrderManagement;
import main.orders.BuyOrderStatus;
import main.orders.OrderProductEntry;
import main.suppliers.SupplierManagement;
import main.utilities.UIUtilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManagementUI {

    private final Scanner scanner = new Scanner(System.in);
    private final SupplierManagement supplierManagement;
    private final ProductManagement productManagement;
    private final BuyOrderManagement buyOrderManagement;

    public InventoryManagementUI(SupplierManagement supplierManagement, ProductManagement productManagement, BuyOrderManagement buyOrderManagement) {
        this.supplierManagement = supplierManagement;
        this.productManagement = productManagement;
        this.buyOrderManagement = buyOrderManagement;

    }

    public void inventoryManagementMenu() {
        while (true) {
            System.out.print("""
                \n
                Inventory Management
                ------------------------
                Please select an option:

                1. View products
                2. Add product
                3. Remove product
                4. Update product details
                5. Order stock
                6. View Deliveries
                7. Back to main menu
                """);

            String inventoryManagementMenuChoice = scanner.nextLine();

            switch (inventoryManagementMenuChoice) {
                case "1":
                    viewProductsMenu();
                    break;
                case "2":
                    addProductMenu();
                    break;
                case "3":
                    removeProductMenu();
                    break;
                case "4":
                    updateProductDetailsMenu();
                    break;
                case "5":
                    orderStockMenu();
                    break;
                case "6":
                    viewBuyOrdersMenu();
                    break;
                case "7":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewProductsMenu() {
        System.out.print("""
                \n
                View Products
                ------------------------
                """);

        UIUtilities.displayProducts(productManagement);
    }

    private void addProductMenu() {
        System.out.print("""
                \n
                Add New Product
                -------------------
                Enter the product's name:
                """);
        String productName = scanner.nextLine();

        UIUtilities.displaySuppliers(supplierManagement);

        int supplierId = 0;
        boolean validSupplierId = false;

        while (!validSupplierId) {
            System.out.println("Enter the product's supplier ID:");
            try {
                supplierId = scanner.nextInt();
                if (!supplierManagement.getSuppliers().containsKey(supplierId)) {
                    System.out.println("Invalid supplier ID, please try again.");
                    continue;
                }
                validSupplierId = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer value for the supplier ID.");
                scanner.nextLine();
            }
        }

        double productBuyPrice = 0;
        boolean validBuyPrice = false;

        while (!validBuyPrice) {
            System.out.println("Enter the product's buy price:");
            try {
                productBuyPrice = scanner.nextDouble();
                if (productBuyPrice < 0) {
                    System.out.println("Buy price cannot be negative, please try again.");
                    continue;
                }
                validBuyPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a decimal number for the buy price.");
                scanner.nextLine();
            }
        }

        double productSellPrice = 0;
        boolean validSellPrice = false;

        while (!validSellPrice) {
            System.out.println("Enter the product's sell price:");
            try {
                productSellPrice = scanner.nextDouble();
                if (productSellPrice < 0) {
                    System.out.println("Sell price cannot be negative, please try again.");
                    continue;
                }
                validSellPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a decimal number for the sell price.");
                scanner.nextLine();
            }
        }

        int productInitialStock = 0;
        boolean validInitialStock = false;

        while (!validInitialStock) {
            System.out.println("Enter the product's initial stock:");
            try {
                productInitialStock = scanner.nextInt();
                if (productInitialStock < 0) {
                    System.out.println("Initial stock cannot be negative, please try again.");
                    continue;
                }
                validInitialStock = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer for the initial stock.");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Do you want to add a product with these details? (y or n)");
            System.out.println("Name: " + productName);
            System.out.println("Supplier: " + supplierManagement.getSupplierById(supplierId).getName());
            System.out.println("Buy Price: £" + productBuyPrice);
            System.out.println("Sell Price: £" + productSellPrice);

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                productManagement.addProduct(productName, productBuyPrice, productSellPrice, productInitialStock, supplierId);
                break;

            } else if (confirmation.equalsIgnoreCase("n")) {
                addProductMenu();
            }

            System.out.println("Invalid input, try again");
        }
    }

    private void removeProductMenu() {
        System.out.print("""
                \n
                Remove Product
                -------------------
                """);

        UIUtilities.displayProductsForSale(productManagement);
        System.out.println("Enter the ID of the product you want to remove:");
        int userChoice = scanner.nextInt();
        productManagement.removeProduct(userChoice);
    }

    private void updateProductDetailsMenu() {
        System.out.print("""
                \n
                Update Product Details
                -------------------
                """);
        UIUtilities.displayProductsForSale(productManagement);
        System.out.println("Enter the ID of the product you want to update:");
        int idToUpdate = scanner.nextInt();


        System.out.print("""
                1. Name
                2. Sell Price
                3. Buy Price
                Select what you would like to update:
                """);
        String updateProductDetailsMenuChoice = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Enter the new value:");

        try {
            switch (updateProductDetailsMenuChoice) {
                case "1":
                    String newName = scanner.nextLine();
                    productManagement.updateName(idToUpdate, newName);
                    break;
                case "2":
                    double newSellPrice = scanner.nextDouble();
                    productManagement.updateSellPrice(idToUpdate, newSellPrice);
                    break;
                case "3":
                    double newBuyPrice = scanner.nextDouble();
                    productManagement.updateBuyPrice(idToUpdate, newBuyPrice);
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } catch (SupplierNotFoundException supplierNotFound) {
            System.out.println(supplierNotFound.getMessage());
        }
    }

    private void orderStockMenu() {
        ArrayList<OrderProductEntry> orderProducts = new ArrayList<>();

        System.out.print("""
                \n
                Order Stock
                -------------------
                """);

        boolean validSupplierId = false;
        int supplierId = 0;
        while (!validSupplierId) {
            UIUtilities.displaySuppliers(supplierManagement);
            System.out.println("Enter the supplier ID you want to order from:");
            try {
                supplierId = scanner.nextInt();
                if (!supplierManagement.getSuppliers().containsKey(supplierId)) {
                    System.out.println("Invalid supplier ID, please try again.");
                    continue;
                }
                validSupplierId = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer value for the supplier ID.");
                scanner.nextLine();
            }
        }

        boolean anotherEntry = true;
        while (anotherEntry) {
            UIUtilities.displayProductsToBuyFromSupplier(supplierManagement, supplierId);

            int idToOrder = 0;
            int quantityToOrder = 0;
            boolean validId = false;

            while (!validId) {
                System.out.println("Enter the ID of the product you want to order:");
                try {
                    idToOrder = scanner.nextInt();
                    if (!supplierManagement.getSupplierById(supplierId).getAvailableProducts().contains(productManagement.getProducts().get(idToOrder))) {
                        System.out.println("Invalid product ID, please try again.");
                        continue;
                    }
                    validId = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    scanner.nextLine();
                }
            }

            boolean validQuantity = false;

            while (!validQuantity) {
                System.out.println("Enter the quantity you want to order:");
                try {
                    quantityToOrder = scanner.nextInt();
                    validQuantity = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    scanner.nextLine();
                }
            }

            OrderProductEntry orderProductEntry = new OrderProductEntry(productManagement.getProducts().get(idToOrder), quantityToOrder);
            orderProducts.add(orderProductEntry);

            System.out.println("Do you want to add another product to the order? (y or n)");
            scanner.nextLine();
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("n")) {
                anotherEntry = false;
            } else if (!confirmation.equalsIgnoreCase("y")) {
                System.out.println("Invalid input, try again");
            }
        }

        try {
            buyOrderManagement.createOrder(orderProducts);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Order created successfully!");
    }

    private void viewBuyOrdersMenu() {
        while (true) {
            System.out.print("""
                    \n
                    View Orders
                    ------------------------
                    """);

            if (buyOrderManagement.getOrders().isEmpty()) {
                System.out.println("No orders found.");
            }

            for (Integer key : buyOrderManagement.getOrders().keySet()) {
                System.out.println("Order ID: " + buyOrderManagement.getOrders().get(key).getId());
                System.out.println("Status: " +  buyOrderManagement.getOrders().get(key).getStatus());
                System.out.println("------------------------");
            }

            System.out.print("""
                    \n
                    Please select an option:

                    1. Refresh
                    2. Accept a Delivery
                    3. Back to main menu
                    """);

            String viewOrdersMenuChoice = scanner.nextLine();

            switch (viewOrdersMenuChoice) {
                case "1":
                    viewBuyOrdersMenu();
                    break;
                case "2":
                    System.out.println("Orders Ready for Delivery:");
                    for (Integer key : buyOrderManagement.getOrders().keySet()) {
                        if (buyOrderManagement.getOrders().get(key).getStatus() == BuyOrderStatus.READY_FOR_DELIVERY) {
                            System.out.println("Order ID: " +  buyOrderManagement.getOrders().get(key).getId());
                            System.out.println("------------------------");
                        }
                    }

                    int orderId = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.println("Enter the ID of the order you want to accept:");
                        try {
                            orderId = scanner.nextInt();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer.");
                            scanner.nextLine();
                        }
                    }
                    buyOrderManagement.acceptDelivery(orderId);
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }
}
