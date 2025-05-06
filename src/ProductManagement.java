import java.util.HashMap;

public class ProductManagement {
    HashMap<Integer, Product> products = new HashMap<>();
    private int nextId = 1;

    public ProductManagement() {

    }

    public void addProduct(String name, double sellPrice, double buyPrice, int initialStock) {
        products.put(nextId, new Product(name, sellPrice, buyPrice, initialStock));
        nextId++;
    }

    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    public void removeProduct(int id) {
        if (!products.containsKey(id)) {
            System.out.println("Invalid product ID.");
            return;
        }
        products.remove(id);
    }
}
