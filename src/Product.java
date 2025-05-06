public class Product {
    private final int id;
    private String name;
    private int quantityInStock;
    private double sellPrice;
    private double buyPrice;

    public Product(int id, String name, int quantityInStock, double sellPrice, double buyPrice) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

    public int getId() {
        return this.id;
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
