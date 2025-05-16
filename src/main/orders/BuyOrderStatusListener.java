package main.orders;

public interface BuyOrderStatusListener {
    void onStatusChanged(BuyOrder order);
}
