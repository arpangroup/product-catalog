package com.arpan.productcatalog.entity.enums;

public enum ProductType {
    PRODUCT("Product");

    public final String label;

    private ProductType(String label) {
        this.label = label;
    }
}
