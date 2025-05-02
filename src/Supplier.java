import java.util.ArrayList;

public class Supplier {
    private String name;
    private String phoneNumber;
    private ArrayList<Product> productsAvailable;

    public Supplier(String name, String phoneNumber, ArrayList<Product> productsAvailable) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.productsAvailable = productsAvailable;
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

    public void addAvailableProducts(ArrayList<Product> productsAvailable) {
        this.productsAvailable.addAll(productsAvailable);
    }
}
