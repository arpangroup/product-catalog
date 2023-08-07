package com.arpan.productcatalog.service;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.entity.product.ProductCategory;
import com.arpan.productcatalog.exception.ValidationException;

public interface CategoryService {
    ProductCategory createNewProductCategory(CategoryCreateRequest request) throws ValidationException;
    ProductCategory getCategoryById(int id);
}
