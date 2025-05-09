import java.util.ArrayList;

public class Order {
    private final int id;
    private final ArrayList<OrderProductEntry> products;

    public Order(int id, ArrayList<OrderProductEntry> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (OrderProductEntry entry : products) {
            total += entry.product().getBuyPrice() * entry.quantity();
        }
        return total;
    }
}