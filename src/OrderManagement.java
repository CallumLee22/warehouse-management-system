import java.util.HashMap;
import java.util.ArrayList;

public abstract class OrderManagement<T> {
    private final ProductManagement productManagement;
    protected int nextOrderId = 1;
    protected final HashMap<Integer, T> orders = new HashMap<>();

    public OrderManagement(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    public HashMap<Integer, T> getOrders() {
        return orders;
    }

    public void createOrder(ArrayList<OrderProductEntry> products) {
        T order = createOrderInstance(nextOrderId, products);
        orders.put(nextOrderId, order);
        nextOrderId++;

        for (OrderProductEntry entry : products) {
            int productId = entry.product().getId();
            int quantity = entry.quantity();

            updateStock(productId, quantity);
        }
    }

    protected abstract T createOrderInstance(int orderId, ArrayList<OrderProductEntry> products);

    protected void updateStock(int productId, int quantity) {
        Product product = productManagement.getProducts().get(productId);
        if (product != null) {
            int newStock = product.getStock() + quantity;
            product.setStock(newStock);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + productId + " was not found when updating stock");
        }
    }
}
