package com.arpan.productcatalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
public class StoreCreateRequest implements Serializable {
    @NotBlank(message = "storeName is mandatory")
    @Size(min = 4, max = 50, message = "storeName must be between 2 and 32 characters long")
    private String storeName;
    private String externalReferenceId;

    public StoreCreateRequest(@NonNull String storeName) {
        this.storeName = storeName;
    }
    public StoreCreateRequest(@NonNull String storeName, String externalReferenceId) {
        this(storeName);
        this.externalReferenceId = externalReferenceId;
    }
}
