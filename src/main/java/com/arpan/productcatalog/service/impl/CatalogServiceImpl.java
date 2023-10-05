package com.arpan.productcatalog.service.impl;

import com.arpan.productcatalog.dto.CatalogSummary;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.mapper.CatalogMapper;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.service.CatalogService;
import com.arpan.productcatalog.validatior.CatalogValidator;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository repository;
    private final CatalogMapper mapper;
    private final CatalogValidator validator;


    @Override
    public List<CatalogSummary> getAllCatalogSummary() {
        return repository.findAll().stream().map(mapper::mapToSummary).collect(Collectors.toList());
    }

    @Override
    public List<CatalogSummary> getAllCatalogSummary(Long storeId) {
        return repository.findByStores_Id(storeId).stream().map(mapper::mapToSummary).collect(Collectors.toList());
    }

    @Override
    public Catalog createNewCatalog(String catalogName) throws ValidationException {
        validator.validateCatalogName(catalogName);
        Catalog catalog = new Catalog(catalogName);
        return repository.save(catalog);
    }

    @Override
    public Catalog createNewCatalog(Long storeId, String catalogName) throws ValidationException {
//        Store store = validator.validateStoreIdAndCatalogName(storeId, catalogName);
//        Catalog catalog = new Catalog(catalogName);
//        catalog.attachStore(store);
//        return repository.save(catalog);
        return null;
    }

    @Override
    public Catalog updateCatalogName(Long catalogId, String newName) throws IdNotFoundException, ValidationException {
        Catalog catalog = repository.findById(catalogId).orElseThrow(() -> new IdNotFoundException("catalogId not found"));
        catalog.setName(newName);
        try {
            return repository.save(catalog);
        } catch (ConstraintViolationException ex1) {
            AtomicReference<String> errorMessage = new AtomicReference<>("");
            ex1.getConstraintViolations().forEach(cex -> {
                var message = cex.getMessage();
                var contentDescriptor = cex.getConstraintDescriptor();
                var invalidValue = cex.getInvalidValue();
                var attributes = cex.getConstraintDescriptor().getAttributes();
                var path = cex.getPropertyPath();
                errorMessage.set(cex.getMessage());
                System.out.println(cex.getMessage());
            });
            throw new ValidationException(errorMessage.get());
        } catch (Exception ex2) {
            throw new ValidationException(ex2.getMessage());
        }
    }


    @Override
    public Catalog changeOwner(Long catalogId, String newUser) throws ValidationException {
        return null;
    }

    @Override
    public List<Catalog> getAllCatalogs() {
        return repository.findAll();
    }

    @Override
    public List<Catalog> getAllCatalogsByStoreId(Long storeId) {
        return repository.findByStores_Id(storeId);
    }

    @Override
    public Catalog getCatalogsDetails(Long catalogId) throws IdNotFoundException{
        return repository.findById(catalogId).orElseThrow(() -> new IdNotFoundException("catalogId not found"));
    }
}
