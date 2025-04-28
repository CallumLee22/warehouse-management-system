public class Product {
    private int id;
    private String name;
    private int quantityInStock;

    public Product(int id, String name, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }
}
