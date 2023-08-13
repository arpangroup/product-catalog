package com.arpan.productcatalog.service;

import com.arpan.productcatalog.dto.CatalogSummary;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;

import java.util.List;

public interface CatalogService {
    List<CatalogSummary> getAllCatalogSummary();
    List<CatalogSummary> getAllCatalogSummary(Long storeId);
    Catalog createNewCatalog(Long storeId, String catalogName) throws ValidationException;
    Catalog updateCatalogName(Long catalogId, String newName) throws IdNotFoundException, ValidationException;
    Catalog changeOwner(Long catalogId, String newUser) throws ValidationException;
    List<Catalog> getAllCatalogs();
    List<Catalog> getAllCatalogsByStoreId(Long storeId);
    Catalog getCatalogsDetails(Long catalogId) throws IdNotFoundException;
}
