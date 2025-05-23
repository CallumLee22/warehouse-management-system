package main.orders;

import main.exceptions.ProductNotFoundException;
import main.inventory.Product;
import main.inventory.ProductManagement;
import main.inventory.ProductStockListener;

import java.util.HashMap;
import java.util.ArrayList;

public abstract class OrderManagement<T> {
    protected final ProductManagement productManagement;
    protected int nextOrderId = 1;
    protected final HashMap<Integer, T> orders = new HashMap<>();
    private ProductStockListener stockListener;

    public OrderManagement(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    public HashMap<Integer, T> getOrders() {
        return orders;
    }

    public abstract void createOrder(ArrayList<OrderProductEntry> products);

    protected abstract T createOrderInstance(int orderId, ArrayList<OrderProductEntry> products);

    protected void updateStock(int productId, int quantity) {
        Product product = productManagement.getProducts().get(productId);
        if (product != null) {
            int newStock = product.getStock() + quantity;
            product.setStock(newStock);
            if (product.getStock() <= 20 && stockListener != null) {
                stockListener.onLowStock(product);
            }
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + productId + " was not found when updating stock");
        }
    }

    public void setLowStockListener(ProductStockListener listener) {
        this.stockListener = listener;
    }

    protected void checkInvalidEntry(ArrayList<OrderProductEntry> products, boolean isSellOrder) {
        for (OrderProductEntry entry : products) {
            if (entry.product() == null) {
                throw new ProductNotFoundException("Invalid product in order");

            }

            if (entry.quantity() <= 0) {
                throw new IllegalArgumentException("Invalid quantity in order");
            }

            if (isSellOrder && entry.product().getStock() < entry.quantity()) {
                throw new IllegalArgumentException("Not enough stock for product " + entry.product().getName());
            }
        }
    }
}
