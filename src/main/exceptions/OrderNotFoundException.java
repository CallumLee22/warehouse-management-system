package main.exceptions;

/**
 * Exception thrown when an order is not found.
 * This exception extends RuntimeException and is used to indicate that a specific order
 * could not be located in the system.
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
