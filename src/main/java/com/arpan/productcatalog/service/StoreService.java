package com.arpan.productcatalog.service;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleCatalogResponse;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;

import java.util.List;

public interface StoreService {
    public WebStore createStore(StoreCreateRequest request) throws ValidationException;
    public List<SimpleStoreResponse> getAllStore();
    public SimpleCatalogResponse getAllStoreByCatalogId(Long catalogId);
    public WebStore getStoreDetailsById(Long id) throws IdNotFoundException;
    public WebStore getStoreDetailsByStoreName(String storeName) throws ValidationException;
    public WebStore getStoreDetailsByReferenceID(String referenceId) throws ValidationException;
}
