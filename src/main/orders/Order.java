package main.orders;

import java.util.ArrayList;

public abstract class Order {
    private final int id;
    protected final ArrayList<OrderProductEntry> products;
    private final double totalPrice;

    public Order(int id, ArrayList<OrderProductEntry> products) {
        this.id = id;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    public int getId() {
        return id;
    }

    protected abstract double calculateTotalPrice();

    public ArrayList<OrderProductEntry> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}