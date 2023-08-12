package com.arpan.productcatalog.dto.response;

import com.arpan.productcatalog.dto.AuditUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDetailResponse {
    private Long categoryId;
    private String categoryName;
    private Long catalogId;
    private Long storeId;
    private Long parentCategoryId;
    private String parentCategoryName;
    private String description;
    private boolean isActive;
    private int sortOrder;
    private int numberOfProducts;
    private Date activeFrom;
    private Date activeTo;
    private AuditUser creator;
    private AuditUser lastUpdater;
    private List<SimpleProductResponse> products;
}
