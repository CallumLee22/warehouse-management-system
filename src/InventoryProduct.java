public class InventoryProduct extends Product {
    private int quantityInStock;

    public InventoryProduct(String name, double price, int initialStock) {
        super(name, price);
        this.quantityInStock = initialStock;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
