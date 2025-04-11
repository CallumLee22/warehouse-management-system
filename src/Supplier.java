import java.util.ArrayList;

public class Supplier {
    private int id;
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
}
