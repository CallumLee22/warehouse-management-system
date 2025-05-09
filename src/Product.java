public class Product {
    private final int id;
    private final int supplierId;
    private String name;
    private double buyPrice;
    private double sellPrice;
    private int stock;

    public Product(int id, int supplierId, String name, double buyPrice, double sellPrice, int stock) {
        this.id = id;
        this.supplierId = supplierId;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
    }

    public int getId() {
        return this.id;
    }

    public int getSupplierId() {
        return this.supplierId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(double newPrice) {
        this.buyPrice = newPrice;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public void setSellPrice(double newPrice) {
        this.sellPrice = newPrice;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int newStock) {
        this.stock = newStock;
    }
}
