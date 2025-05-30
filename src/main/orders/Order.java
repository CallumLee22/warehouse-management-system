package main.orders;

import java.util.ArrayList;

/**
 * Represents a generic order in the inventory system.
 */
public abstract class Order {
    private final int id;
    protected final ArrayList<OrderProductEntry> products;
    private final double totalPrice;

    /**
     * Constructs an Order with the specified ID and products.
     *
     * @param id       the unique identifier for the order
     * @param products the list of products in the order
     */
    public Order(int id, ArrayList<OrderProductEntry> products) {
        this.id = id;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    /**
     * Gets the unique identifier for the order.
     *
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    protected abstract double calculateTotalPrice();

    /**
     * Gets the list of products in the order.
     *
     * @return the list of OrderProductEntry objects
     */
    public ArrayList<OrderProductEntry> getProducts() {
        return products;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price of the order
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}