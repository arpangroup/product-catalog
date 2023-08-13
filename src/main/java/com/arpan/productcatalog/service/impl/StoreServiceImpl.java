package com.arpan.productcatalog.service.impl;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.Store;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.entity.product.Product;
import com.arpan.productcatalog.exception.ErrorCode;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.mapper.StoreMapper;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.repository.StoreRepository;
import com.arpan.productcatalog.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository repository;
    private final CatalogRepository catalogRepository;
    private final StoreMapper mapper;

    @Override
    @Transactional
    public Store createStore(StoreCreateRequest request) throws ValidationException{
        // Step1: if the store already exist, change the store name
        //int existingStore = repository.countDistinctNameByNameIgnoreCase(request.getStoreName());
        int existingStore = repository.countDistinctNameByNameStartsWithIgnoreCase(request.getStoreName());
        if(existingStore > 0) {
            request.setStoreName(request.getStoreName() + "-" + existingStore);
        }

        // Step2: Map the StoreEntity from the request object
        Store store = mapper.mapFrom(request);

        // Step3: Create and Attach Default Catalog
        Catalog catalog = generateDefaultCatalog(store);
        catalog = catalogRepository.save(catalog);
        store.attachCatalog(catalog);

        // Step4: Save the Store into DB
        return repository.save(store);
    }

    @Override
    public List<SimpleStoreResponse> getAllStore() {
        return repository.findAll().stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Store getStoreDetailsById(Long id) throws ValidationException {
        return repository.findById(id).orElseThrow(()-> new ValidationException(ErrorCode.ERROR_STORE_ID_NOT_FOUND));
    }

    @Override
    public Store getStoreDetailsByStoreName(String storeName) throws ValidationException {
        return repository.findByNameIgnoreCase(storeName).orElseThrow(()-> new ValidationException(ErrorCode.ERROR_STORE_ID_NOT_FOUND));
    }

    @Override
    public Store getStoreDetailsByReferenceID(String referenceId) throws ValidationException {
        return repository.findByExternalReferenceId(UUID.fromString(referenceId)).orElseThrow(()-> new ValidationException(ErrorCode.ERROR_STORE_EXTERNAL_REFERENCE_ID_NOT_FOUND));
    }

    private Catalog generateDefaultCatalog(Store store) {
        // Create a default Catalog
        Catalog catalog = new Catalog(store.getSlug() + "-catalog");
        catalog.setDefault(true);
        catalog.setActive(true);
        catalog.setOwnerId("0");
        catalog.setOwnerName("DEFAULT");

        // Create and attach Default Category to the catalog
        Category category = new Category("Products");
        category.setProducts(Arrays.asList(new Product("Product1"), new Product("Product2")));
        catalog.attachCategory(category);

        return catalog;
    }

}
