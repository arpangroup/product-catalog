package com.arpan.productcatalog.entity.enums;

public enum QuantityUnitOfMeasure {
    PICE("pc");

    public final String label;

    private QuantityUnitOfMeasure(String label) {
        this.label = label;
    }
}
