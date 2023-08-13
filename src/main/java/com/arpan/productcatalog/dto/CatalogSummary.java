package com.arpan.productcatalog.dto;

public record CatalogSummary(
        Long catalogId,
        String catalogName,
        int numberOfCategories,
        int numberOfProductsOwned,
        int numberOfProductsAssigned,
        int recommendations,
        String status
) {
    @Override
    public String toString() {
        return "{" +
                "catalogId:" + catalogId +
                ", catalogName:'" + catalogName + '\'' +
                ", numberOfCategories:" + numberOfCategories +
                ", numberOfProductsOwned:" + numberOfProductsOwned +
                ", numberOfProductsAssigned:" + numberOfProductsAssigned +
                ", recommendations:" + recommendations +
                ", status:'" + status + '\'' +
                '}';
    }
}
