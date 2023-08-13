package com.arpan.productcatalog.mapper;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Store;
import com.arpan.productcatalog.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class StoreMapper {

    public SimpleStoreResponse mapTo(Store store) {
        return new SimpleStoreResponse(store.getId(), store.getName(), store.getSlug());
    }

    public Store mapFrom(StoreCreateRequest request) {
        Store store = new Store(request.getStoreName());
        if (request.getExternalReferenceId() !=  null) {
            store.setExternalReferenceId(UUID.fromString(request.getExternalReferenceId()));
        }
        String slug = CommonUtil.toSlug(request.getStoreName());
        store.setSlug(slug);
        return store;
    }

}