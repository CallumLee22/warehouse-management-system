package main.financialReports;

import java.time.LocalDate;

public class FinancialReport {
    private final int id;
    private final double totalRevenue;
    private final double totalExpenses;
    private final double netProfit;
    private final LocalDate reportDate;

    public FinancialReport(int id, double totalRevenue, double totalExpenses) {
        this.id = id;
        this.totalRevenue = totalRevenue;
        this.totalExpenses = totalExpenses;
        this.netProfit = totalRevenue - totalExpenses;
        this.reportDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
