package main.ui;

import main.inventory.Product;
import main.inventory.ProductManagement;
import main.orders.OrderProductEntry;
import main.orders.SellOrderManagement;
import main.utilities.UIUtilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerOrdersUI {
    private final Scanner scanner = new Scanner(System.in);
    private final SellOrderManagement sellOrderManagement;
    private final ProductManagement productManagement;

    public CustomerOrdersUI(SellOrderManagement sellOrderManagement, ProductManagement productManagement) {
        this.sellOrderManagement = sellOrderManagement;
        this.productManagement = productManagement;

    }

    public void customerOrdersMenu() {
        while (true) {
            System.out.print("""
                \n
                Customer Orders
                ------------------------
                Please select an option:

                1. View Customer Orders
                2. Create Customer Order
                3. Back to main menu
                """);

            String customerOrdersMenuChoice = scanner.nextLine();
            switch (customerOrdersMenuChoice) {
                case "1":
                    viewCustomerOrdersMenu();
                    break;
                case "2":
                    createCustomerOrderMenu();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private void viewCustomerOrdersMenu() {
        System.out.print("""
                \n
                View Customer Orders
                ------------------------
                """);

        if (sellOrderManagement.getOrders().isEmpty()) {
            System.out.println("No customer orders found.");
            return;
        }

        for (Integer key : sellOrderManagement.getOrders().keySet()) {
            System.out.println("Order ID: " + sellOrderManagement.getOrders().get(key).getId());
            System.out.println("Products in Order:");
            for (OrderProductEntry orderProductEntry : sellOrderManagement.getOrders().get(key).getProducts()) {
                System.out.println("Product: " + orderProductEntry.product().getName());
                System.out.println("Quantity: " + orderProductEntry.quantity());
            }
            System.out.println("Total Price: £" + sellOrderManagement.getOrders().get(key).getTotalPrice());
            System.out.println("---------------");
        }
    }

    private void createCustomerOrderMenu() {
        ArrayList<OrderProductEntry> orderProducts = new ArrayList<>();

        System.out.print("""
                \n
                Create Customer Order
                ------------------------
                """);

        boolean anotherEntry = true;
        while (anotherEntry) {
            UIUtilities.displayProductsForSale(productManagement);

            boolean validProduct = false;
            int idToOrder = 0;
            while(!validProduct) {
                System.out.println("Enter the ID of the product you want to order:");

                try {
                    idToOrder = scanner.nextInt();

                    if (!productManagement.getProducts().containsKey(idToOrder)) {
                        System.out.println("Please enter a valid ID as listed above");
                        continue;
                    }

                    validProduct = true;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a number.");
                    scanner.nextLine();
                }
            }

            Product productToOrder = productManagement.getProducts().get(idToOrder);

            boolean validQuanity = false;
            int quantityToOrder = 0;
            while (!validQuanity) {
                System.out.println("Enter the quantity you want to order:");
                try {
                    quantityToOrder = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please enter a number.");
                    scanner.nextLine();
                    continue;
                }
                if (productToOrder.getStock() >= quantityToOrder) {
                    validQuanity = true;
                    continue;
                }

                System.out.println("Not enough stock available, please enter a valid quantity.");
            }

            OrderProductEntry orderProductEntry = new OrderProductEntry(productToOrder, quantityToOrder);
            orderProducts.add(orderProductEntry);

            System.out.println("Do you want to add another product to the order? (y or n)");
            scanner.nextLine();
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("n")) {
                anotherEntry = false;
            } else if (!confirmation.equalsIgnoreCase("y")) {
                System.out.println("Invalid input, try again");
            }
        }

        sellOrderManagement.createOrder(orderProducts);

        System.out.println("Customer order created successfully");
    }
}
