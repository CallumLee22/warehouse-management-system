package main.ui;

import main.exceptions.SupplierNotFoundException;
import main.suppliers.SupplierManagement;
import main.utilities.UIUtilities;
import main.utilities.Utilities;

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

        boolean validPhoneNumber = false;
        String supplierPhoneNumber = "";
        while (!validPhoneNumber) {
            System.out.println("Enter the supplier's phone number:");
            supplierPhoneNumber = scanner.nextLine();

            if (Utilities.isValidPhoneNumber(supplierPhoneNumber)) {
                validPhoneNumber = true;
            } else {
                System.err.println("Invalid phone number, please enter a 10-digit number");
            }
        }

        while (true) {
            System.out.println("Do you want to add a supplier with these details? (y or n)");
            System.out.println("Name: " + supplierName);
            System.out.println("Phone number: " + supplierPhoneNumber);

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                supplierManagement.addSupplier(supplierName, supplierPhoneNumber);
                break;

            } else if (confirmation.equalsIgnoreCase("n")) {
                break;
            }

            System.err.println("Invalid input, try again");
        }

        System.out.println("Supplier added successfully.");
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
                scanner.nextLine();
                if(supplierManagement.getSuppliers().containsKey(idToUpdate)) {
                    validInput = true;
                    continue;
                }

                System.err.println("Please enter an ID as listed above");
            } catch (InputMismatchException e) {
                System.err.println("Please enter an integer value for the ID.");
                scanner.nextLine();
            }
        }

        boolean validUpdateChoice = false;
        String updateSupplierMenuChoice = "1";
        while (!validUpdateChoice) {
            System.out.print("""
                1. Name
                2. Phone Number
                Select what you would like to update:
                """);
            updateSupplierMenuChoice = scanner.nextLine();
            scanner.reset();

            if (updateSupplierMenuChoice.equals("1") || updateSupplierMenuChoice.equals("2")) {
                validUpdateChoice = true;
                continue;
            }

            System.err.println("Invalid choice, try again");
        }

        try {
            switch (updateSupplierMenuChoice) {
                case "1":
                    System.out.println("Enter the new name:");
                    String newName = scanner.nextLine();
                    supplierManagement.updateName(idToUpdate, newName);
                    break;
                case "2":

                    String newPhoneNumber;
                    while (true) {
                        System.out.println("Enter the new phone number:");
                        newPhoneNumber = scanner.nextLine();

                        if (Utilities.isValidPhoneNumber(newPhoneNumber)) {
                            break;
                        }

                        System.err.println("Invalid phone number, please enter a 10-digit number");
                    }
                    supplierManagement.updatePhoneNumber(idToUpdate, newPhoneNumber);
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } catch (SupplierNotFoundException supplierNotFound) {
            System.err.println(supplierNotFound.getMessage());
        }

        System.out.println("Supplier updated successfully.");
    }

    private void removeSupplierMenu() {
        System.out.print("""
                \n
                Remove Supplier
                -------------------
                """);

        UIUtilities.displaySuppliers(supplierManagement);

        boolean validInput = false;
        while (!validInput) {
            System.out.println("Enter the ID of the supplier you want to remove:");
            try {
                int userChoice = scanner.nextInt();

                if (!supplierManagement.getSuppliers().containsKey(userChoice)) {
                    System.err.println("Please enter an ID as listed above");
                    continue;
                }

                supplierManagement.removeSupplier(userChoice);
                validInput = true;
            } catch (SupplierNotFoundException supplierNotFound) {
                System.err.println(supplierNotFound.getMessage());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Please enter an integer value for the ID.");
                scanner.nextLine();
            }
        }

        System.out.println("Supplier removed successfully.");
    }
}
