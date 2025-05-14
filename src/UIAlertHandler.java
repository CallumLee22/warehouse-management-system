public class UIAlertHandler implements BuyOrderStatusListener, ProductStockListener {
    @Override
    public void onStatusChanged(BuyOrder order) {
        if (order.getStatus() == BuyOrderStatus.READY_FOR_DELIVERY) {
            System.out.println("##############################################################");
            System.out.println("# ALERT: Order " + order.getId() + " is ready for delivery!  #");
            System.out.println("# Please accept the delivery via the                         #");
            System.out.println("# Inventory Management Menu.                                 #");
            System.out.println("##############################################################");
        }
    }

    @Override
    public void onLowStock(Product product) {
        System.out.println("##############################################################");
        System.out.println("# ALERT: Product [" +product.getId() + "] " + product.getName() + " is low on stock! #");
        System.out.println("# Restock from the inventory management menu.               #");
        System.out.println("##############################################################");
    }
}
