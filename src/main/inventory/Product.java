package main.inventory;

/**
 * Represents a product in the inventory system.
 * Each product has a unique identifier, supplier identifier, name, buying price, selling price, and stock quantity.
 */
public class Product {
    private final int id;
    private String name;
    private double buyPrice;
    private double sellPrice;
    private int stock;

    /**
     * Constructs a Product with the specified parameters.
     *
     * @param id         the unique identifier for the product
     * @param name       the name of the product
     * @param buyPrice   the buying price of the product
     * @param sellPrice  the selling price of the product
     * @param stock      the stock quantity of the product
     */
    public Product(int id, String name, double buyPrice, double sellPrice, int stock) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
    }

    /**
     * Gets the product's ID
     *
     * @return ID of the product
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the product's name
     *
     * @return Name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the product's name
     *
     * @param newName   New name for the product
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the product's buy price
     *
     * @return Buy price of the product
     */
    public double getBuyPrice() {
        return this.buyPrice;
    }

    /**
     * Sets the product's buy price
     *
     * @param newPrice  New buy price for the product
     */
    public void setBuyPrice(double newPrice) {
        this.buyPrice = newPrice;
    }

    /**
     * Gets the product's sell price
     *
     * @return Sell price of the product
     */
    public double getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Sets the product's sell price
     *
     * @param newPrice  New sell price for the product
     */
    public void setSellPrice(double newPrice) {
        this.sellPrice = newPrice;
    }

    /**
     * Gets the product's stock quantity
     *
     * @return Stock quantity of the product
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Sets the product's stock quantity
     *
     * @param newStock  New stock quantity for the product
     */
    public void setStock(int newStock) {
        this.stock = newStock;
    }
}
