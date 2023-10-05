package com.arpan.productcatalog.mapper;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleCatalogResponse;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public final class StoreMapper {

    public SimpleStoreResponse mapTo(WebStore webStore) {
        return new SimpleStoreResponse(webStore.getId(), webStore.getName(), webStore.getSlug());
    }

    public SimpleCatalogResponse mapTo(Catalog catalog, List<WebStore> webStores) {
        List<SimpleStoreResponse> simpleStoreResponseList = webStores.stream().map(this::mapTo).collect(Collectors.toList());
        return new SimpleCatalogResponse(catalog.getId(), catalog.getName(), simpleStoreResponseList);
    }

    public WebStore mapFrom(StoreCreateRequest request) {
        WebStore webStore = new WebStore(request.getStoreName());
        if (request.getExternalReferenceId() !=  null) {
            webStore.setExternalReferenceId(UUID.fromString(request.getExternalReferenceId()));
        }
        String slug = CommonUtil.toSlug(request.getStoreName());
        webStore.setSlug(slug);
        return webStore;
    }

}