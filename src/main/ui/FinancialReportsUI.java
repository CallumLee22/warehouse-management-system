package main.ui;

import main.financialReports.FinancialReport;
import main.financialReports.FinancialReportManagement;

import java.util.Scanner;

/**
 * FinancialReportsUI is a user interface class that allows users to view and create financial reports.
 * It provides a menu-driven interface for interacting with financial reports.
 */
public class FinancialReportsUI {
    private final Scanner scanner = new Scanner(System.in);
    private final FinancialReportManagement financialReportManagement;

    /**
     * Constructor for FinancialReportsUI.
     * Initializes the financial report management system.
     *
     * @param financialReportManagement The financial report management instance to be used.
     */
    public FinancialReportsUI(FinancialReportManagement financialReportManagement) {
        this.financialReportManagement = financialReportManagement;
    }

    /**
     * Displays the financial reports menu and handles user input.
     * Provides options to view reports, create a report, or return to the main menu.
     */
    public void financialReportsMenu() {
        while (true) {
            System.out.print("""
                \n
                Financial Reports
                ------------------------
                Please select an option:

                1. View reports
                2. Create Report
                3. Back to main menu
                """);

            String financialReportsMenuChoice = scanner.nextLine();

            switch (financialReportsMenuChoice) {
                case "1":
                    viewReportsMenu();
                    break;
                case "2":
                    createReportMenu();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewReportsMenu() {
        System.out.print("""
                \n
                View Financial Reports
                ------------------------
                """);

        if (financialReportManagement.getReports() == null || financialReportManagement.getReports().isEmpty()) {
            System.out.println("No financial reports found.");
            System.out.println("Press Enter to return to the menu.");
            scanner.nextLine();
            return;
        }

        for (FinancialReport report : financialReportManagement.getReports()) {
            System.out.println("Report ID: " + report.getId());
            System.out.println("Date: " + report.getReportDate());
            System.out.println("Total Revenue: £" + report.getTotalRevenue());
            System.out.println("Total Expenses: £" + report.getTotalExpenses());
            System.out.println("Net Profit: £" + report.getNetProfit());
            System.out.println("------------------------");
        }

        System.out.println("Press Enter to return to the menu.");
        scanner.nextLine();
    }

    private void createReportMenu() {
        System.out.print("""
                \n
                Create Financial Report
                ------------------------
                """);

        while (true) {
            System.out.println("Are you sure you want to create a financial report? (y or n)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                financialReportManagement.createReport();
                break;
            } else if (confirmation.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println("Invalid input, try again");
            }
        }

        System.out.println("Financial report created successfully");
    }
}
