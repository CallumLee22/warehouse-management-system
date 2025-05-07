import java.util.HashMap;

public class ProductManagement {
    HashMap<Integer, InventoryProduct> inventoryProducts = new HashMap<>();
    private int nextId = 1;

    public ProductManagement() {

    }

    public void addProduct(String name, double price, int initialStock) {
        inventoryProducts.put(nextId, new InventoryProduct(name, price, initialStock));
        nextId++;
    }

    public HashMap<Integer, InventoryProduct> getInventoryProducts() {
        return inventoryProducts;
    }

    public void removeProduct(int id) {
        if (!inventoryProducts.containsKey(id)) {
            System.out.println("Invalid product ID.");
            return;
        }
        inventoryProducts.remove(id);
    }

    public void updateName(int id, String newName) {
        InventoryProduct product = inventoryProducts.get(id);
        if (product != null) {
            product.setName(newName);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating name");
        }
    }

    public void updatePrice(int id, double newPrice) {
        InventoryProduct product = inventoryProducts.get(id);
        if (product != null) {
            product.setPrice(newPrice);
        } else {
            throw new ProductNotFoundException(
                    "Inventory product with ID " + id + " was not found when updating price");
        }
    }
}
