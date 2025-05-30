package main.orders;

import main.inventory.ProductManagement;

import java.util.ArrayList;

/**
 * Manages sell orders in the inventory system.
 * Extends the OrderManagement class and provides functionality specific to sell orders.
 */
public class SellOrderManagement extends OrderManagement<SellOrder> {

    /**
     * Constructs a SellOrderManagement with the specified ProductManagement instance.
     *
     * @param productManagement the ProductManagement instance to manage products
     */
    public SellOrderManagement(ProductManagement productManagement) {
        super(productManagement);
    }

    @Override
    protected SellOrder createOrderInstance(int orderId, ArrayList<OrderProductEntry> products) {
        return new SellOrder(orderId, products);
    }

    @Override
    public void createOrder(ArrayList<OrderProductEntry> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be empty.");
        }

        checkInvalidEntry(products, true);

        SellOrder order = createOrderInstance(nextOrderId, products);
        orders.put(nextOrderId, order);
        nextOrderId++;

        for (OrderProductEntry entry : products) {
            int productId = entry.product().getId();
            int quantity = entry.quantity();
            updateStock(productId, -quantity);
        }
    }
}
