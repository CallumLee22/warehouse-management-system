package main.orders;

import main.exceptions.OrderNotFoundException;

import main.inventory.ProductManagement;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BuyOrderManagement extends OrderManagement<BuyOrder> {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private BuyOrderStatusListener statusListener;

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

    public void setStatusListener(BuyOrderStatusListener listener) {
        this.statusListener = listener;
    }
}
