package com.arpan.productcatalog.dto.response;

public record SimpleProductResponse(
        Long productId,
        String productName,
        String productCode,
        String sku,
        String description,
        String displayUrl,
        String externalId
){

}