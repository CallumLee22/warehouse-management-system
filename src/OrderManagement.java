import java.util.ArrayList;

public abstract class OrderManagement<T> {
    private final ProductManagement productManagement;
    protected int nextOrderId = 1;
    protected final ArrayList<T> orders = new ArrayList<>();

    public OrderManagement(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    public ArrayList<T> getOrders() {
        return orders;
    }

    public void createOrder(ArrayList<OrderProductEntry> products) {
        T order = createOrderInstance(nextOrderId, products);
        orders.add(order);
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
