package com.arpan.productcatalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
public class CategoryCreateRequest implements Serializable {
    @NotBlank(message = "categoryName is mandatory")
    private String categoryName;
    private Integer catalogId;
    private Integer parentCategoryId;
    private String description;
    private boolean isActive;
    private Integer sortOrder;
    private Date activeFrom;
    private Date activeTo;
}
