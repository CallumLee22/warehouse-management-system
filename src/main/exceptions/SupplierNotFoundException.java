package main.exceptions;

/**
 * Exception thrown when a supplier is not found.
 * This exception extends RuntimeException and is used to indicate that a specific supplier
 * could not be located in the system.
 */
public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
