package com.arpan.productcatalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateRequest implements Serializable {
    @NotBlank(message = "categoryName is mandatory")
    @Size(min = 4, max = 50, message = "categoryName must be between 2 and 32 characters long")
    private String categoryName;
    private Integer parentCategoryId;
    private String description;
    private boolean isActive;
    private Integer sortOrder;

    public CategoryCreateRequest(String categoryName) {
        this.categoryName = categoryName;
    }
}
