package main.exceptions;

/**
 * Exception thrown when a product is not found.
 * This exception extends RuntimeException and is used to indicate that a specific product
 * could not be located in the system.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
