package com.arpan.productcatalog.mapper;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.dto.response.CategoryDetailResponse;
import com.arpan.productcatalog.dto.response.SimpleCategoryResponse;
import com.arpan.productcatalog.entity.product.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public SimpleCategoryResponse mapTo(Category category) {
        return new SimpleCategoryResponse(category.getId(), category.getName());
    }
    public CategoryDetailResponse mapToDetails(Category category) {
        return new CategoryDetailResponse().builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                //.catalogId()
                //.storeId()
                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .parentCategoryName(category.getParentCategory() != null ? category.getParentCategory().getName() : null)
                .description(category.getDescription())
                .isActive(category.isActive())
                .sortOrder(category.getSortOrder() != null ? category.getSortOrder() : 0)
                //.numberOfProducts(category.getProducts().size())
                .activeFrom(category.getActiveFrom())
                .activeTo(category.getActiveTo())
                //.creator()
                //.lastUpdater()
                //.products()
                .build();
    }
    public Category mapFrom(CategoryCreateRequest request, Category parentCategory) {
        return Category.builder()
                .name(request.getCategoryName())
                .parentCategory(parentCategory)
                .description(request.getDescription())
                .isActive(request.isActive())
                .sortOrder(request.getSortOrder())
                .build();

    }
}