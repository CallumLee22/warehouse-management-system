import java.util.ArrayList;

public class SellOrderManagement extends OrderManagement<SellOrder> {

    public SellOrderManagement(ProductManagement productManagement) {
        super(productManagement);
    }

    @Override
    protected SellOrder createOrderInstance(int orderId, ArrayList<OrderProductEntry> products) {
        return new SellOrder(orderId, products);
    }

    @Override
    public void createOrder(ArrayList<OrderProductEntry> products) {
        SellOrder order = createOrderInstance(nextOrderId, products);
        orders.put(nextOrderId, order);
        nextOrderId++;

        for (OrderProductEntry entry : products) {
            int productId = entry.product().getId();
            int quantity = entry.quantity();
            updateStock(productId, -quantity);
        }
    }
}
