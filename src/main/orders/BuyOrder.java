package main.orders;

import java.util.ArrayList;

/**
 * Represents a buy order in the inventory system.
 * Extends the Order class and includes specific functionality for buy orders.
 */
public class BuyOrder extends Order {
    private BuyOrderStatus status;

    /**
     * Constructs a BuyOrder with the specified ID and products.
     *
     * @param id       the unique identifier for the order
     * @param products the list of products in the order
     */
    public BuyOrder(int id, ArrayList<OrderProductEntry> products) {
        super(id, products);
        this.status = BuyOrderStatus.PROCESSING;
    }

    @Override
    protected double calculateTotalPrice() {
        double total = 0;
        for (OrderProductEntry entry : products) {
            total += entry.product().getBuyPrice() * entry.quantity();
        }
        return total;
    }

    /**
     * Returns the status of the buy order.
     *
     * @return Current status of the buy order
     */
    public BuyOrderStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the buy order.
     *
     * @param status    New status to set for the buy order
     */
    public void setStatus(BuyOrderStatus status) {
        this.status = status;
    }
}
