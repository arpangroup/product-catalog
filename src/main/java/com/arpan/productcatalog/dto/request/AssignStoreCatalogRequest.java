package com.arpan.productcatalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignStoreCatalogRequest implements Serializable {
    @NotNull(message = "storeId should not be null or empty")
    private Long storeId;
    @NotNull(message = "catalogId should not be null or empty")
    private Long catalogId;
}
