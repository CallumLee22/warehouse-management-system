package main.inventory;

public interface ProductStockListener {
    void onLowStock(Product product);
}
