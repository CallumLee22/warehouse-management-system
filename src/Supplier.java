import java.util.ArrayList;

public class Supplier {
    private final int id;
    private String name;
    private String phoneNumber;
    private ArrayList<Product> productsAvailable;

    public Supplier(int id, String name, String phoneNumber, ArrayList<Product> productsAvailable) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.productsAvailable = productsAvailable;
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

    public void addAvailableProducts(Product product) {
        this.productsAvailable.add(product);
    }
}
