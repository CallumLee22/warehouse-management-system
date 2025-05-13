import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);
    private final SellOrderManagement sellOrderManagement = new SellOrderManagement(productManagement);
    private final FinancialReportManagement financialReportManagement = new FinancialReportManagement(buyOrderManagement, sellOrderManagement);
    private final UIAlertHandler alertHandler = new UIAlertHandler();

    public void mainMenu() {
        supplierManagement.addSupplier("Example Supplier", "1234567890");
        productManagement.addProduct("Example Product", 10.0, 15.0, 100, 1);

        buyOrderManagement.setStatusListener(alertHandler);
        while (true) {
            System.out.print("""
                    \n
                    Welcome to the Warehouse Management System
                    ------------------------------------------
                    Please select an option:

                    1. Inventory Management
                    2. Supplier Management
                    3. Customer Orders
                    4. Financial Reports
                    5. Exit
                    """);

            String mainMenuUserChoice = scanner.nextLine();

            switch (mainMenuUserChoice) {
                case "1":
                    inventoryManagementMenu();
                    break;
                case "2":
                    supplierManagementMenu();
                    break;
                case "3":
                    customerOrdersMenu();
                    break;
                case "4":
                    financialReportsMenu();
                    break;
                case "5":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.err.println("Invalid option, try again");
                    break;
            }
        }
    }

    private void inventoryManagementMenu() {
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

    private void displayProductsForSale() {
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

    private void displayProductsToBuy() {
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

    private void viewProductsMenu() {
        System.out.print("""
                \n
                View Products
                ------------------------
                """);

        displayProductsForSale();
    }

    private void addProductMenu() {
        System.out.print("""
                \n
                Add New Product
                -------------------
                Enter the product's name:
                """);
        String productName = scanner.nextLine();

        displaySuppliers();

        System.out.println("Enter the product's supplier ID:");
        int supplierId = scanner.nextInt();

        System.out.println("Enter the product's buy price:");
        double productBuyPrice = scanner.nextDouble();

        System.out.println("Enter the product's sell price:");
        double productSellPrice = scanner.nextDouble();

        System.out.println("Enter the product's initial stock:");
        int productInitialStock = scanner.nextInt();

        while (true) {
            System.out.println("Do you want to add a product with these details? (y or n)");
            System.out.println("Name: " + productName);
            System.out.println("Supplier: " + supplierManagement.getSupperlierById(supplierId).getName());
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

        displayProductsForSale();
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
        displayProductsForSale();
        System.out.println("Enter the ID of the product you want to update:");
        int idToUpdate = scanner.nextInt();

        System.out.print("""
                1. Name
                2. Sell Price
                3. Buy Price
                Select what you would like to update:
                """);
        int updateProductDetailsMenuChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new value:");

        try {
            switch (updateProductDetailsMenuChoice) {
                case 1:
                    String newName = scanner.nextLine();
                    productManagement.updateName(idToUpdate, newName);
                    break;
                case 2:
                    double newSellPrice = scanner.nextDouble();
                    productManagement.updateSellPrice(idToUpdate, newSellPrice);
                    break;
                case 3:
                    double newBuyPrice = scanner.nextDouble();
                    productManagement.updateBuyPrice(idToUpdate, newBuyPrice);
                    break;
                default:
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

        boolean anotherEntry = true;
        while (anotherEntry) {
            displayProductsToBuy();
            System.out.println("Enter the ID of the product you want to order stock for:");
            int idToOrder = scanner.nextInt();
            System.out.println("Enter the quantity you want to order:");
            int quantityToOrder = scanner.nextInt();

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

        buyOrderManagement.createOrder(orderProducts);

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
                    System.out.println("Enter the ID of the order you want to accept:");
                    int orderId = scanner.nextInt();
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

    private void supplierManagementMenu() {
        while (true) {
            System.out.print("""
                    \n
                    Supplier Management
                    ------------------------
                    Please select an option:

                    1. View suppliers
                    2. Add supplier
                    3. Update supplier
                    4. Remove supplier
                    5. Back to main menu
                    """);

            String supplierManagementMenuChoice = scanner.nextLine();

            switch (supplierManagementMenuChoice) {
                case "1":
                    viewSuppliersMenu();
                    break;
                case "2":
                    addSupplierMenu();
                    break;
                case "3":
                    updateSupplierMenu();
                    break;
                case "4":
                    removeSupplierMenu();
                    break;
                case "5":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void displaySuppliers() {
        if (supplierManagement.getSuppliers().isEmpty()) {
            System.out.println("No suppliers found.");
            return;
        }

        for (Integer key : supplierManagement.getSuppliers().keySet()) {
            System.out
                    .println("[" + key + "] " + supplierManagement.getSupperlierById(key).getName()
                            + " - " + supplierManagement.getSupperlierById(key).getPhoneNumber());
        }
    }

    private void viewSuppliersMenu() {
        System.out.print("""
                \n
                View Suppliers
                ------------------------
                """);

        displaySuppliers();
    }

    private void addSupplierMenu() {

        System.out.print("""
                \n
                Add New Supplier
                -------------------
                Enter the supplier's name:
                """);

        String supplierName = scanner.nextLine();

        System.out.println("Enter the suppliers phone number:");

        String supplierPhoneNumber = scanner.nextLine();

        while (true) {
            System.out.println("Do you want to add a supplier with these details? (y or n)");
            System.out.println("Name: " + supplierName);
            System.out.println("Phone number: " + supplierPhoneNumber);

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                supplierManagement.addSupplier(supplierName, supplierPhoneNumber);
                break;

            } else if (confirmation.equalsIgnoreCase("n")) {
                addSupplierMenu();
            }

            System.out.println("Invalid input, try again");
        }
    }

    private void updateSupplierMenu() {
        System.out.print("""
                \n
                Update Supplier
                -------------------
                """);

        displaySuppliers();
        System.out.println("Enter the ID of the supplier you want to update:");
        int idToUpdate = scanner.nextInt();
        System.out.print("""
                1. Name
                2. Phone Number
                Select what you would like to update:
                """);
        int updateSupplierMenuChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        try {
            switch (updateSupplierMenuChoice) {
                case 1:
                    supplierManagement.updateName(idToUpdate, newValue);
                    break;
                case 2:
                    supplierManagement.updatePhoneNumber(idToUpdate, newValue);
                    break;
                default:
                    break;
            }
        } catch (SupplierNotFoundException supplierNotFound) {
            System.out.println(supplierNotFound.getMessage());
        }
    }

    private void removeSupplierMenu() {
        System.out.print("""
                \n
                Remove Supplier
                -------------------
                """);

        displaySuppliers();
        System.out.println("Enter the ID of the supplier you want to remove:");
        try {
            int userChoice = scanner.nextInt();
            supplierManagement.removeSupplier(userChoice);
        } catch (SupplierNotFoundException supplierNotFound) {
            System.out.println(supplierNotFound.getMessage());
        }
    }

    private void customerOrdersMenu() {
        while (true) {
            System.out.print("""
                \n
                Customer Orders
                ------------------------
                Please select an option:

                1. View Customer Orders
                2. Create Customer Order
                3. Back to main menu
                """);

            String customerOrdersMenuChoice = scanner.nextLine();
            switch (customerOrdersMenuChoice) {
                case "1":
                    viewCustomerOrdersMenu();
                    break;
                case "2":
                    createCustomerOrderMenu();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewCustomerOrdersMenu() {
        System.out.print("""
                \n
                View Customer Orders
                ------------------------
                """);

        if (sellOrderManagement.getOrders().isEmpty()) {
            System.out.println("No customer orders found.");
            return;
        }

        for (Integer key : sellOrderManagement.getOrders().keySet()) {
            System.out.println("Order ID: " + sellOrderManagement.getOrders().get(key).getId());
            System.out.println("Products in Order:");
            for (OrderProductEntry orderProductEntry : sellOrderManagement.getOrders().get(key).getProducts()) {
                System.out.println("Product: " + orderProductEntry.product().getName());
                System.out.println("Quantity: " + orderProductEntry.quantity());
            }
            System.out.println("Total Price: £" + sellOrderManagement.getOrders().get(key).getTotalPrice());
            System.out.println("---------------");
        }
    }

    private void createCustomerOrderMenu() {
        ArrayList<OrderProductEntry> orderProducts = new ArrayList<>();

        System.out.print("""
                \n
                Create Customer Order
                ------------------------
                """);

        boolean anotherEntry = true;
        while (anotherEntry) {
            displayProductsForSale();
            System.out.println("Enter the ID of the product you want to order:");
            int idToOrder = scanner.nextInt();
            System.out.println("Enter the quantity you want to order:");
            int quantityToOrder = scanner.nextInt();

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

        sellOrderManagement.createOrder(orderProducts);

        System.out.println("Customer order created successfully!");
    }

    private void financialReportsMenu() {
        while (true) {
            System.out.print("""
                \n
                Financial Reports
                ------------------------
                Please select an option:

                1. View reports
                2. Create Report
                3. Back to main menu
                """);

            String financialReportsMenuChoice = scanner.nextLine();

            switch (financialReportsMenuChoice) {
                case "1":
                    viewReportsMenu();
                    break;
                case "2":
                    createReportMenu();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewReportsMenu() {
        System.out.print("""
                \n
                View Financial Reports
                ------------------------
                """);

        if (financialReportManagement.getReports() == null || financialReportManagement.getReports().isEmpty()) {
            System.out.println("No financial reports found.");
            System.out.println("Press Enter to return to the menu.");
            scanner.nextLine();
            return;
        }

        for (FinancialReport report : financialReportManagement.getReports()) {
            System.out.println("Report ID: " + report.getId());
            System.out.println("Date: " + report.getReportDate());
            System.out.println("Total Revenue: £" + report.getTotalRevenue());
            System.out.println("Total Expenses: £" + report.getTotalExpenses());
            System.out.println("Net Profit: £" + report.getNetProfit());
            System.out.println("------------------------");
        }

        System.out.println("Press Enter to return to the menu.");
        scanner.nextLine();
    }

    private void createReportMenu() {
        System.out.print("""
                \n
                Create Financial Report
                ------------------------
                """);

        while (true) {
            System.out.println("Are you sure you want to create a financial report? (y or n)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                financialReportManagement.createReport();
                break;
            } else if (confirmation.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println("Invalid input, try again");
            }
        }

        System.out.println("Financial report created successfully!");
    }
}
