import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private static SupplierManagement supplierManagement = new SupplierManagement();

    public void mainMenu() {
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

            String userChoice = scanner.nextLine();

            switch (userChoice) {
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
        System.out.print("""
                    Inventory Management
                    ------------------------
                    Please select an option:

                    1. View stock
                    2. Add stock
                    3. Remove stock
                    4. Order stock
                    5. Back to main menu
                """);
    }

    private void supplierManagementMenu() {
        while (true) {
            System.out.print("""
                        Supplier Management
                        ------------------------
                        Please select an option:

                        1. View suppliers
                        2. Add supplier
                        3. Update supplier
                        4. Remove supplier
                        5. Back to main menu
                    """);

            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
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
                    .println("[" + key + "] " + supplierManagement.getSuppliers().get(key).getName()
                            + " - " + supplierManagement.getSuppliers().get(key).getPhoneNumber());
        }
    }

    private void viewSuppliersMenu() {
        System.out.print("""
                    View Suppliers
                    ------------------------
                """);

        displaySuppliers();
    }

    private void addSupplierMenu() {

        System.out.print("""
                    Add New Supplier
                    -------------------
                    Enter the supplier's name:
                """);

        String supplierName = scanner.nextLine();

        System.out.println("Enter the suppliers phone number:");

        String supplierPhoneNumber = scanner.nextLine();

        boolean confirm = false;

        while (!confirm) {
            System.out.println("Do you want to add a supplier with these details? (y or n)");
            System.out.println("Name: " + supplierName);
            System.out.println("Phone number: " + supplierPhoneNumber);

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                confirm = true;
                supplierManagement.addSupplier(supplierName, supplierPhoneNumber);
                break;

            } else if (confirmation.equalsIgnoreCase("n")) {
                addSupplierMenu();
            }

            System.out.println("Invalid input, try again");
        }

        supplierManagementMenu();
    }

    private void updateSupplierMenu() {
        System.out.print("""
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
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        try {
            switch (userChoice) {
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

        supplierManagementMenu();
    }

    private void removeSupplierMenu() {
        System.out.print("""
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

        supplierManagementMenu();
    }

    private void financialReportsMenu() {
        System.out.print("""
                    Financial Reports
                    ------------------------
                    Please select an option:

                    1. View reports
                    2. Create Report
                    3. Back to main menu
                """);
    }

    private void customerOrdersMenu() {
        System.out.print("""
                    Customer Orders
                    ------------------------
                    Please select an option:

                    1. View Orders
                    2. Back to main menu
                """);
    }
}
