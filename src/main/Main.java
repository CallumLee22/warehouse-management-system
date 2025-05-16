package main;

import main.financialReports.FinancialReportManagement;
import main.inventory.ProductManagement;
import main.orders.BuyOrderManagement;
import main.orders.SellOrderManagement;
import main.suppliers.SupplierManagement;
import main.ui.MainMenuUI;
import main.ui.UIAlertHandler;

public class Main {
    public static void main(String[] args) {
        final SupplierManagement supplierManagement = new SupplierManagement();
        final ProductManagement productManagement = new ProductManagement(supplierManagement);
        final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);
        final SellOrderManagement sellOrderManagement = new SellOrderManagement(productManagement);
        final FinancialReportManagement financialReportManagement = new FinancialReportManagement(buyOrderManagement, sellOrderManagement);
        final UIAlertHandler alertHandler = new UIAlertHandler();

        MainMenuUI ui = new MainMenuUI(supplierManagement, productManagement, buyOrderManagement, sellOrderManagement, financialReportManagement, alertHandler);
        ui.mainMenu();
    }
}
