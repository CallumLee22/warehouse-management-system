import java.util.ArrayList;

public class SellOrderManagement extends OrderManagement<SellOrder> {

    public SellOrderManagement(ProductManagement productManagement) {
        super(productManagement);
    }

    @Override
    protected SellOrder createOrderInstance(int orderId, ArrayList<OrderProductEntry> products) {
        return new SellOrder(orderId, products);
    }
}
