package main.ui;

import main.financialReports.FinancialReportManagement;
import main.inventory.ProductManagement;
import main.orders.BuyOrderManagement;
import main.orders.SellOrderManagement;
import main.suppliers.SupplierManagement;

import java.util.Scanner;

public class MainMenuUI {
    private final Scanner scanner = new Scanner(System.in);
    private final SupplierManagement supplierManagement;
    private final ProductManagement productManagement;
    private final BuyOrderManagement buyOrderManagement;
    private final UIAlertHandler alertHandler;
    private final InventoryManagementUI inventoryManagementUI;
    private final SupplierManagementUI supplierManagementUI;
    private final CustomerOrdersUI customerOrdersUI;
    private final FinancialReportsUI financialReportsUI;
    private final SellOrderManagement sellOrderManagement;

    public MainMenuUI(SupplierManagement supplierManagement, ProductManagement productManagement, BuyOrderManagement buyOrderManagement, SellOrderManagement sellOrderManagement, FinancialReportManagement financialReportManagement, UIAlertHandler alertHandler) {
        this.supplierManagement = supplierManagement;
        this.productManagement = productManagement;
        this.buyOrderManagement = buyOrderManagement;
        this.alertHandler = alertHandler;
        this.sellOrderManagement = sellOrderManagement;

        this.inventoryManagementUI = new InventoryManagementUI(supplierManagement, productManagement, buyOrderManagement);
        this.supplierManagementUI = new SupplierManagementUI(supplierManagement);
        this.customerOrdersUI = new CustomerOrdersUI(sellOrderManagement, productManagement);
        this.financialReportsUI = new FinancialReportsUI(financialReportManagement);
    }

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
