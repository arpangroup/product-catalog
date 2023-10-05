package com.arpan.productcatalog.validatior;

import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.exception.ErrorCode;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CatalogValidator {
    private final CatalogRepository repository;

    private boolean validateCatalogNameLength(String catalogName) throws ValidationException {
        if (catalogName == null) throw new ValidationException(ErrorCode.ERROR_CATALOG_NAME_SHOULD_NOT_BE_NULL);
        if (catalogName.length() < 4 || catalogName.length() > 50) throw new ValidationException(ErrorCode.ERROR_CATALOG_NAME_INVALID_LENGTH);
        return true;
    }
    private boolean validateCatalogNameUnique(String catalogName) throws ValidationException {
        long noOfCatalog = repository.countByNameIgnoreCase(catalogName);
        if (noOfCatalog > 1) throw new ValidationException(ErrorCode.ERROR_DUPLICATE_CATALOG);
        return true;
    }


    public boolean validateCatalogName(String catalogName) throws ValidationException {
        // step1: validate categoryName
        validateCatalogNameLength(catalogName);
        validateCatalogNameUnique(catalogName);
        return true;
    }

    public WebStore validateStoreIdAndCatalogName(Long storeId, String catalogName) throws ValidationException {
        // step1: validate categoryName
        validateCatalogNameLength(catalogName);

        // Step2: Check whether storeId is valid or not, if valid then get the store
        final List<Catalog> storeCatalogs = repository.findByStores_Id(storeId);
        if (storeCatalogs.size() == 0) throw new ValidationException(ErrorCode.ERROR_STORE_ID_NOT_FOUND); // Step1.3: check whether the catalogName is already present or not, then throw error

        // step3: Check whether cate
        storeCatalogs.stream().filter(catalog -> catalog.getName().equalsIgnoreCase(catalogName)).findAny().orElseThrow(() -> new ValidationException(ErrorCode.ERROR_DUPLICATE_CATALOG));

//        Store storeObj = storeCatalogs.stream().map(Catalog::getStores).flatMap(Collection::stream).filter(store -> store.getId().equals(storeId)).findFirst().get();
//        return storeObj;
        return null;
    }
}
