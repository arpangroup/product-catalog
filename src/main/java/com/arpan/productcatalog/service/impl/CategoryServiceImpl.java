package com.arpan.productcatalog.service.impl;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.entity.product.ProductCategory;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CategoryRepository;
import com.arpan.productcatalog.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductCategory createNewProductCategory(CategoryCreateRequest request) throws ValidationException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(request.getCategoryName());
        return categoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
