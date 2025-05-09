import java.util.ArrayList;

public class OrderManagement {
    private ProductManagement productManagement;
    private int nextBuyOrderId = 1;
    private ArrayList<BuyOrder> buyOrders = new ArrayList<>();

    public OrderManagement(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    public ArrayList<BuyOrder> getBuyOrders() {
        return buyOrders;
    }

    public void createBuyOrder(ArrayList<OrderProductEntry> products) {
        BuyOrder order = new BuyOrder(nextBuyOrderId, products);
        buyOrders.add(order);
        nextBuyOrderId++;

        for (OrderProductEntry entry : products) {
            int productId = entry.product().getId();
            int quantity = entry.quantity();
            updateStock(productId, quantity);
        }
    }

    private void updateStock(int productId, int quantity) {
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
