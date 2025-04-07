import java.util.Scanner;

public class VisualInterface {
    public void mainMenu() {
        Scanner mainMenuScanner = new Scanner(System.in);

        System.out.print("""

            Welcome to the Warehouse Management System
            ------------------------------------------
            Please select an option:

            1. Inventory Management
            2. Supplier Management
            3. Customer Orders
            4. Financial Reports
        """);
        
        String userChoice = mainMenuScanner.nextLine();
        
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
            default:
                System.err.println("Invalid option, try again");
                mainMenu();
                break;
        }

        mainMenuScanner.close();
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
