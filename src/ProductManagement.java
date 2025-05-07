import java.util.HashMap;

public class ProductManagement {
    HashMap<Integer, InventoryProduct> inventoryProducts = new HashMap<>();
    private int nextId = 1;

    public ProductManagement() {

    }

    public void addProduct(String name, double sellPrice, double buyPrice, int initialStock) {
        inventoryProducts.put(nextId, new InventoryProduct(name, sellPrice, initialStock));
        nextId++;
    }

    public HashMap<Integer, InventoryProduct> getInventoryProductHashMap() {
        return inventoryProducts;
    }

    public void removeProduct(int id) {
        if (!inventoryProducts.containsKey(id)) {
            System.out.println("Invalid product ID.");
            return;
        }
        inventoryProducts.remove(id);
    }
}
