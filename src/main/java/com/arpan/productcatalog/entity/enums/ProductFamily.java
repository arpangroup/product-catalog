package com.arpan.productcatalog.entity.enums;

public enum ProductFamily {
    NONE("None");

    public final String label;

    private ProductFamily(String label) {
        this.label = label;
    }
}
