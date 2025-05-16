package main.orders;

import main.inventory.Product;

public record OrderProductEntry(Product product, int quantity) {
}
