package main.orders;

import java.util.ArrayList;

/**
 * Represents a sell order in the inventory system.
 * Extends the Order class and includes specific functionality for sell orders.
 */
public class SellOrder extends Order {

    /**
     * Constructs a SellOrder with the specified ID and products.
     *
     * @param id       the unique identifier for the order
     * @param products the list of products in the order
     */
    public SellOrder(int id, ArrayList<OrderProductEntry> products) {
        super(id, products);
    }

    @Override
    protected double calculateTotalPrice() {
        double total = 0;
        for (OrderProductEntry entry : products) {
            total += entry.product().getSellPrice() * entry.quantity();
        }
        return total;
    }
}
