package com.arpan.productcatalog.dto.response;

public record ProductResponse (
        Long productId,
        String productName,
        String productCode,
        String sku,
        String description,
        String displayUrl,
        String externalId
){

}