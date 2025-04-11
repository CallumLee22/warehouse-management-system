public class Product {
    private int id;
    private String name;
    private int quantityInStock;

    public Product(String name, int quantityInStock) {
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }
}
