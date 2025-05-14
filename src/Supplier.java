import java.util.ArrayList;

public class Supplier {
    private final int id;
    private String name;
    private String phoneNumber;
    private final ArrayList<Product> productsAvailable = new ArrayList<>();

    public Supplier(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public ArrayList<Product> getAvailableProducts() {
        return this.productsAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addAvailableProduct(Product product) {
        this.productsAvailable.add(product);
    }
}
