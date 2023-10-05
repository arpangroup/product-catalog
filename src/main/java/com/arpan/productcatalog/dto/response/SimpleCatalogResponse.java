package com.arpan.productcatalog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleCatalogResponse {
    private Long catalogId;
    private String catalogName;
    private List<SimpleStoreResponse> stores;
}
