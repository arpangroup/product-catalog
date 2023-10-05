package com.arpan.productcatalog.mapper;

import com.arpan.productcatalog.dto.CatalogSummary;
import com.arpan.productcatalog.dto.response.SimpleCategoryResponse;
import com.arpan.productcatalog.entity.Catalog;
import org.springframework.stereotype.Component;

@Component
public final class CatalogMapper {
    public CatalogSummary mapToSummary(Catalog catalog) {
        return new CatalogSummary(
                catalog.getId(),
                catalog.getName(),
                catalog.getCategories().size(),
                0,
                0,
                0,
                catalog.getIsActive() != null && catalog.getIsActive() ? "ONLINE" : "OFFLINE"
        );
    }
}