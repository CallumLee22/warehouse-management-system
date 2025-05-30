package main.orders;

import main.exceptions.OrderNotFoundException;

import main.inventory.ProductManagement;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Manages buy orders in the inventory system.
 * Extends the OrderManagement class and provides specific functionality for buy orders.
 */
public class BuyOrderManagement extends OrderManagement<BuyOrder> {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private BuyOrderStatusListener statusListener;

    /**
     * Constructs a BuyOrderManagement instance with the specified ProductManagement.
     *
     * @param productManagement     The ProductManagement instance to use for product operations
     */
    public BuyOrderManagement(ProductManagement productManagement) {
        super(productManagement);
    }

    @Override
    public void createOrder(ArrayList<OrderProductEntry> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be empty.");
        }

        checkInvalidEntry(products, false);

        BuyOrder order = createOrderInstance(nextOrderId, products);
        orders.put(nextOrderId, order);
        nextOrderId++;

        // Simulating products taking time to arrive
        scheduler.scheduleAtFixedRate(() -> {
            updateOrderStatus(order);
            if (order.getStatus() == BuyOrderStatus.DELIVERED) {
                scheduler.shutdown();
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    protected BuyOrder createOrderInstance(int orderId, ArrayList<OrderProductEntry> products) {
        return new BuyOrder(orderId, products);
    }

    /**
     * Accepts delivery of a buy order by updating its status and adjusting the stock of products.
     *
     * @param orderId   The ID of the order to accept delivery for
     */
    public void acceptDelivery(int orderId) {
        BuyOrder order = getOrders().get(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order with ID " + orderId + " was not found when accepting delivery");
        }
        order.setStatus(BuyOrderStatus.DELIVERED);

        for (OrderProductEntry entry : order.getProducts()) {
            int productId = entry.product().getId();
            int quantity = entry.quantity();

            updateStock(productId, quantity);
        }
    }

    private void updateOrderStatus(BuyOrder order) {
        if (order.getStatus() == BuyOrderStatus.PROCESSING) {
            order.setStatus(BuyOrderStatus.SHIPPED);
        } else if (order.getStatus() == BuyOrderStatus.SHIPPED) {
            order.setStatus(BuyOrderStatus.READY_FOR_DELIVERY);
            if (statusListener != null) {
                statusListener.onStatusChanged(order);
            }
        }
    }

    /**
     * Sets a listener to be notified of buy order status changes.
     *
     * @param listener The listener to set
     */
    public void setStatusListener(BuyOrderStatusListener listener) {
        this.statusListener = listener;
    }
}
