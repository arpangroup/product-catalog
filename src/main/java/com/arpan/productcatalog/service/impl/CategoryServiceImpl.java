package com.arpan.productcatalog.service.impl;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.dto.response.CategoryDetailResponse;
import com.arpan.productcatalog.dto.response.SimpleCategoryResponse;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.ErrorCode;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.mapper.CategoryMapper;
import com.arpan.productcatalog.repository.CategoryRepository;
import com.arpan.productcatalog.service.CategoryService;
import com.arpan.productcatalog.validatior.CategoryRequestValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final CategoryRequestValidator validator;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryRequestValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public List<SimpleCategoryResponse> getAllCategories() {
        return repository.findAll().stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public List<SimpleCategoryResponse> getAllCategories(long storeId, long catalogId) {
        return null;
    }

    @Override
    public List<SimpleCategoryResponse> getAllCategoriesByStoreId(long storeId) {
        return null;
    }

    @Override
    public List<SimpleCategoryResponse> getAllCategoriesByCatalogId(long catalogId) {
        return null;
    }

    @Override
    public CategoryDetailResponse getCategoryDetailsByCategoryId(long categoryId) throws ValidationException {
        Category category = repository.findById(categoryId).orElseThrow(() -> new ValidationException(ErrorCode.ERROR_CATEGORY_ID_NOT_FOUND));
        return mapper.mapToDetails(category);
    }

    @Override
    public Category createNewProductCategory(@Valid CategoryCreateRequest request) throws ValidationException {
        boolean exists = repository.existsByName(request.getCategoryName());
        
        Category category = mapper.mapFrom(request, null);
        return repository.save(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return null;
    }
}
