package main.financialReports;

import java.time.LocalDate;

/**
 * Represents a financial report with details about revenue, expenses, and profit.
 */
public class FinancialReport {
    private final int id;
    private final double totalRevenue;
    private final double totalExpenses;
    private final double netProfit;
    private final LocalDate reportDate;

    /**
     * Constructs a FinancialReport with the specified parameters.
     *
     * @param id            the unique identifier for the report
     * @param totalRevenue  the total revenue generated
     * @param totalExpenses the total expenses incurred
     */
    public FinancialReport(int id, double totalRevenue, double totalExpenses) {
        this.id = id;
        this.totalRevenue = totalRevenue;
        this.totalExpenses = totalExpenses;
        this.netProfit = totalRevenue - totalExpenses;
        this.reportDate = LocalDate.now();
    }

    /**
     * Gets the ID of the financial report
     *
     * @return ID of the financial report
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the total revenue of the financial report
     *
     * @return Total revenue of the financial report
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Gets the total expenses of the financial report
     *
     * @return Total expenses of the financial report
     */
    public double getTotalExpenses() {
        return totalExpenses;
    }

    /**
     * Gets the net profit of the financial report
     *
     * @return Net profit of the financial report
     */
    public double getNetProfit() {
        return netProfit;
    }

    /**
     * Gets the date of the financial report
     *
     * @return Date of the financial report
     */
    public LocalDate getReportDate() {
        return reportDate;
    }
}
