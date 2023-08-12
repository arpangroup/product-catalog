package com.arpan.productcatalog.service;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Store;
import com.arpan.productcatalog.exception.ValidationException;

import java.util.List;

public interface StoreService {
    public Store createStore(StoreCreateRequest request) throws ValidationException;
    public List<SimpleStoreResponse> getAllStore();
    public Store getStoreDetailsById(Long id) throws ValidationException;
    public Store getStoreDetailsByStoreName(String storeName) throws ValidationException;
    public Store getStoreDetailsByReferenceID(String referenceId) throws ValidationException;
}
