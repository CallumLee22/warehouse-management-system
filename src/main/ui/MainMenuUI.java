package main.ui;

import main.financialReports.FinancialReportManagement;
import main.inventory.ProductManagement;
import main.orders.BuyOrderManagement;
import main.orders.SellOrderManagement;
import main.suppliers.SupplierManagement;

import java.util.Scanner;

/**
 * MainMenuUI class provides the main menu interface for the Warehouse Management System.
 * It allows users to navigate through different functionalities such as Inventory Management,
 * Supplier Management, Customer Orders, and Financial Reports.
 */
public class MainMenuUI {
    private final Scanner scanner = new Scanner(System.in);
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);
    private final SellOrderManagement sellOrderManagement = new SellOrderManagement(productManagement);
    private final FinancialReportManagement financialReportManagement = new FinancialReportManagement(buyOrderManagement, sellOrderManagement);
    private final UIAlertHandler alertHandler = new UIAlertHandler();
    private final InventoryManagementUI inventoryManagementUI = new InventoryManagementUI(supplierManagement, productManagement, buyOrderManagement);
    private final SupplierManagementUI supplierManagementUI = new SupplierManagementUI(supplierManagement);
    private final CustomerOrdersUI customerOrdersUI = new CustomerOrdersUI(sellOrderManagement, productManagement);
    private final FinancialReportsUI financialReportsUI = new FinancialReportsUI(financialReportManagement);

    /**
     * Constructor for MainMenuUI.
     */
    public MainMenuUI() {

    }

    /**
     * Displays the main menu and handles user input to navigate through the system.
     */
    public void mainMenu() {
        // Pre-populate some mock data
        supplierManagement.addSupplier("Example Supplier", "1234567890");
        productManagement.addProduct("Example Product", 10.0, 15.0, 100, 1);
        productManagement.addProduct("Laptop", 125.0, 150.0, 50, 1);
        productManagement.addProduct("Cushion", 15.0, 17.0, 200, 1);
        productManagement.addProduct("TV", 250.0, 300, 100, 1);

        supplierManagement.addSupplier("Example Supplier2", "0987654321");
        productManagement.addProduct("Example Product2", 10.0, 15.0, 100, 2);

        buyOrderManagement.setStatusListener(alertHandler);
        sellOrderManagement.setLowStockListener(alertHandler);

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
                    inventoryManagementUI.inventoryManagementMenu();
                    break;
                case "2":
                    supplierManagementUI.supplierManagementMenu();
                    break;
                case "3":
                    customerOrdersUI.customerOrdersMenu();
                    break;
                case "4":
                    financialReportsUI.financialReportsMenu();
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
}
