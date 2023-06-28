package com.arpan.productcatalog.entity.enums;

public enum ProductClass {
    SIMPLE("Simple");

    public final String label;

    private ProductClass(String label) {
        this.label = label;
    }
}
