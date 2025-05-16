package tests;

import main.financialReports.FinancialReportManagement;
import main.inventory.ProductManagement;
import main.orders.BuyOrderManagement;
import main.orders.OrderProductEntry;
import main.orders.SellOrderManagement;
import main.suppliers.SupplierManagement;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FinancialReportManagementTests {
    private final SupplierManagement supplierManagement = new SupplierManagement();
    private final ProductManagement productManagement = new ProductManagement(supplierManagement);
    private final BuyOrderManagement buyOrderManagement = new BuyOrderManagement(productManagement);
    private final SellOrderManagement sellOrderManagement = new SellOrderManagement(productManagement);
    private final FinancialReportManagement financialReportManagement = new FinancialReportManagement(buyOrderManagement, sellOrderManagement);

    @Test
    public void testCreateFinancialReport_NoOrders() {
        financialReportManagement.createReport();

        assertEquals(1, financialReportManagement.getReports().size());
        assertEquals(0.0, financialReportManagement.getReports().getFirst().getTotalRevenue(), 0.01);
        assertEquals(0.0, financialReportManagement.getReports().getFirst().getTotalExpenses(), 0.01);
        assertEquals(0.0, financialReportManagement.getReports().getFirst().getNetProfit(), 0.01);
    }

    @Test
    public void testCreateFinancialReport_WithOrders() {
        supplierManagement.addSupplier("Supplier1", "1234567890");
        productManagement.addProduct("Product1", 10.0, 5.0, 100, 1);

        OrderProductEntry productEntry = new OrderProductEntry(productManagement.getProducts().get(1), 5);
        ArrayList<OrderProductEntry> productsForOrder = new ArrayList<>();
        productsForOrder.add(productEntry);
        buyOrderManagement.createOrder(productsForOrder);
        buyOrderManagement.acceptDelivery(1);

        financialReportManagement.createReport();
        assertEquals(1, financialReportManagement.getReports().size());
        assertEquals(0.0, financialReportManagement.getReports().getFirst().getTotalRevenue(), 0.01);
        assertEquals(50.0, financialReportManagement.getReports().getFirst().getTotalExpenses(), 0.01);
        assertEquals(-50.0, financialReportManagement.getReports().getFirst().getNetProfit(), 0.01);
    }
}
