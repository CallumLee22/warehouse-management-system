package main.orders;

import java.util.ArrayList;

public class SellOrder extends Order {
    public SellOrder(int id, ArrayList<OrderProductEntry> products) {
        super(id, products);
    }
}
