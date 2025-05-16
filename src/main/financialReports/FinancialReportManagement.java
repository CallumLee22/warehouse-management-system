package main.financialReports;

import main.orders.BuyOrderManagement;
import main.orders.SellOrderManagement;

import java.util.ArrayList;

public class FinancialReportManagement {
    private final BuyOrderManagement buyOrderManagement;
    private final SellOrderManagement sellOrderManagement;
    private final ArrayList<FinancialReport> reports = new ArrayList<>();

    public FinancialReportManagement(BuyOrderManagement buyOrderManagement, SellOrderManagement sellOrderManagement) {
        this.buyOrderManagement = buyOrderManagement;
        this.sellOrderManagement = sellOrderManagement;
    }

    public void createReport() {
        double totalRevenue = calculateTotalRevenue();
        double totalExpenses = calculateTotalExpense();
        reports.add(new FinancialReport(reports.size() + 1, totalRevenue, totalExpenses));
    }

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
