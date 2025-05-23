package main.orders;

import java.util.ArrayList;

public class SellOrder extends Order {
    public SellOrder(int id, ArrayList<OrderProductEntry> products) {
        super(id, products);
    }

    @Override
    protected double calculateTotalPrice() {
        double total = 0;
        for (OrderProductEntry entry : products) {
            total += entry.product().getSellPrice() * entry.quantity();
        }
        return total;
    }
}
