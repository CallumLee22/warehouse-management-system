package main.financialReports;

import main.orders.BuyOrderManagement;
import main.orders.SellOrderManagement;

import java.util.ArrayList;

/**
 * Manages the creation and retrieval of financial reports based on buy and sell orders.
 */
public class FinancialReportManagement {
    private final BuyOrderManagement buyOrderManagement;
    private final SellOrderManagement sellOrderManagement;
    private final ArrayList<FinancialReport> reports = new ArrayList<>();

    /**
     * Constructs a FinancialReportManagement with the specified buy and sell order management systems.
     *
     * @param buyOrderManagement  the management system for buy orders
     * @param sellOrderManagement the management system for sell orders
     */
    public FinancialReportManagement(BuyOrderManagement buyOrderManagement, SellOrderManagement sellOrderManagement) {
        this.buyOrderManagement = buyOrderManagement;
        this.sellOrderManagement = sellOrderManagement;
    }

    /**
     * Creates a financial report based on the current buy and sell orders.
     * The report includes total revenue from sell orders and total expenses from buy orders.
     */
    public void createReport() {
        double totalRevenue = calculateTotalRevenue();
        double totalExpenses = calculateTotalExpense();
        reports.add(new FinancialReport(reports.size() + 1, totalRevenue, totalExpenses));
    }

    /**
     * Retrieves the list of financial reports.
     *
     * @return an ArrayList of FinancialReport objects
     */
    public ArrayList<FinancialReport> getReports() {
        return reports;
    }

    private double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Integer key : sellOrderManagement.getOrders().keySet()) {
            totalRevenue += sellOrderManagement.getOrders().get(key).getTotalPrice();
        }
        return totalRevenue;
    }

    private double calculateTotalExpense() {
        double totalExpense = 0;
        for (Integer key : buyOrderManagement.getOrders().keySet()) {
            totalExpense += buyOrderManagement.getOrders().get(key).getTotalPrice();
        }
        return totalExpense;
    }
}
