package com.arpan.productcatalog;

public record Customer(int id, String name) {
    public String getFullName() {
        return name.toUpperCase();
    }
}
