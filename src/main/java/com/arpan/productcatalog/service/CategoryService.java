package com.arpan.productcatalog.service;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.dto.response.CategoryDetailResponse;
import com.arpan.productcatalog.dto.response.SimpleCategoryResponse;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.ValidationException;

import java.util.List;

public interface CategoryService {
    List<SimpleCategoryResponse> getAllCategories();
    List<SimpleCategoryResponse> getAllCategories(long storeId, long catalogId);
    List<SimpleCategoryResponse> getAllCategoriesByStoreId(long storeId);
    List<SimpleCategoryResponse> getAllCategoriesByCatalogId(long catalogId);
    CategoryDetailResponse getCategoryDetailsByCategoryId(long categoryId) throws ValidationException;
    Category createNewProductCategory(CategoryCreateRequest request) throws ValidationException;
    Category getCategoryById(int id);
}
