import java.util.ArrayList;

public class Order {
    private final int id;
    private final ArrayList<OrderProductEntry> products;
    private final double totalPrice;

    public Order(int id, ArrayList<OrderProductEntry> products) {
        this.id = id;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    public int getId() {
        return id;
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (OrderProductEntry entry : products) {
            total += entry.product().getBuyPrice() * entry.quantity();
        }
        return total;
    }

    public ArrayList<OrderProductEntry> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}