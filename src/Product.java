public class Product {
    private String name;
    private int quantityInStock;
    private double sellPrice;
    private double buyPrice;

    public Product(String name, double sellPrice, double buyPrice, int quantityInStock) {
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(int newQuantity) {
        this.quantityInStock = newQuantity;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public void setSellPrice(double newSellPrice) {
        this.sellPrice = newSellPrice;
    }

    public double getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(double newBuyPrice) {
        this.buyPrice = newBuyPrice;
    }
}
