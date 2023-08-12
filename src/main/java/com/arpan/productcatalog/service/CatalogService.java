package com.arpan.productcatalog.service;

import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;

import java.util.List;

public interface CatalogService {
    Catalog createNewCatalog(Long storeId, String categoryName);
    Catalog updateCatalogName(Long catalogId, String newName) throws IdNotFoundException;
    Catalog addNewCatalog(Long storeId, String catalogName) throws ValidationException;
    Catalog changeOwner(Long catalogId, String newUser) throws ValidationException;
    List<Catalog> getAllCatalogs();
    List<Catalog> getAllCatalogsByStoreId(Long storeId);
    Catalog getCatalogsDetails(Long catalogId);
}
