import java.util.ArrayList;

public class BuyOrder extends Order {
    private BuyOrderStatus status;

    public BuyOrder(int id, ArrayList<OrderProductEntry> products) {
        super(id, products);
        this.status = BuyOrderStatus.PROCESSING;
    }

    public BuyOrderStatus getStatus() {
        return status;
    }

    public void setStatus(BuyOrderStatus status) {
        this.status = status;
    }
}
