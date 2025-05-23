package main.orders;

import java.util.ArrayList;

public class BuyOrder extends Order {
    private BuyOrderStatus status;

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

    public BuyOrderStatus getStatus() {
        return status;
    }

    public void setStatus(BuyOrderStatus status) {
        this.status = status;
    }
}
