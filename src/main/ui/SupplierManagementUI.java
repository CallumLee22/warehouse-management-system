package main.ui;

import main.exceptions.SupplierNotFoundException;
import main.suppliers.SupplierManagement;
import main.utilities.UIUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SupplierManagementUI {

    private final Scanner scanner = new Scanner(System.in);
    private final SupplierManagement supplierManagement;

    public SupplierManagementUI(SupplierManagement supplierManagement) {
        this.supplierManagement = supplierManagement;
    }

    public void supplierManagementMenu() {
        while (true) {
            System.out.print("""
                    \n
                    Supplier Management
                    ------------------------
                    Please select an option:

                    1. View suppliers
                    2. Add supplier
                    3. Update supplier
                    4. Remove supplier
                    5. Back to main menu
                    """);

            String supplierManagementMenuChoice = scanner.nextLine();

            switch (supplierManagementMenuChoice) {
                case "1":
                    viewSuppliersMenu();
                    break;
                case "2":
                    addSupplierMenu();
                    break;
                case "3":
                    updateSupplierMenu();
                    break;
                case "4":
                    removeSupplierMenu();
                    break;
                case "5":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewSuppliersMenu() {
        System.out.print("""
                \n
                View Suppliers
                ------------------------
                """);

        UIUtilities.displaySuppliers(supplierManagement);
    }

    private void addSupplierMenu() {

        System.out.print("""
                \n
                Add New Supplier
                -------------------
                Enter the supplier's name:
                """);

        String supplierName = scanner.nextLine();

        System.out.println("Enter the suppliers phone number:");

        String supplierPhoneNumber = scanner.nextLine();

        while (true) {
            System.out.println("Do you want to add a supplier with these details? (y or n)");
            System.out.println("Name: " + supplierName);
            System.out.println("Phone number: " + supplierPhoneNumber);

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                supplierManagement.addSupplier(supplierName, supplierPhoneNumber);
                break;

            } else if (confirmation.equalsIgnoreCase("n")) {
                addSupplierMenu();
            }

            System.out.println("Invalid input, try again");
        }
    }

    private void updateSupplierMenu() {
        System.out.print("""
                \n
                Update Supplier
                -------------------
                """);

        UIUtilities.displaySuppliers(supplierManagement);

        int idToUpdate = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the ID of the supplier you want to update:");
            try {
                idToUpdate = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer value for the ID.");
                scanner.nextLine();
            }
        }

        System.out.print("""
                1. Name
                2. Phone Number
                Select what you would like to update:
                """);
        String updateSupplierMenuChoice = scanner.nextLine();
        scanner.nextLine();

        try {
            switch (updateSupplierMenuChoice) {
                case "1":
                    System.out.println("Enter the new name:");
                    String newName = scanner.nextLine();
                    supplierManagement.updateName(idToUpdate, newName);
                    break;
                case "2":
                    System.out.println("Enter the new phone number:");
                    String newPhoneNumber = scanner.nextLine();
                    supplierManagement.updatePhoneNumber(idToUpdate, newPhoneNumber);
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } catch (SupplierNotFoundException supplierNotFound) {
            System.out.println(supplierNotFound.getMessage());
        }
    }

    private void removeSupplierMenu() {
        System.out.print("""
                \n
                Remove Supplier
                -------------------
                """);

        UIUtilities.displaySuppliers(supplierManagement);
        System.out.println("Enter the ID of the supplier you want to remove:");
        try {
            int userChoice = scanner.nextInt();
            supplierManagement.removeSupplier(userChoice);
        } catch (SupplierNotFoundException supplierNotFound) {
            System.out.println(supplierNotFound.getMessage());
        }
    }
}
