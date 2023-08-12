package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.dto.response.CategoryDetailResponse;
import com.arpan.productcatalog.dto.response.SimpleCategoryResponse;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CategoryRepository;
import com.arpan.productcatalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired CategoryService service;
    @Autowired CategoryRepository repository;


    @GetMapping("")
    public ResponseEntity<List<SimpleCategoryResponse>> getAllCategory() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDetailResponse> getAllCategoryById(@PathVariable long categoryId) throws ValidationException {
        return ResponseEntity.ok(service.getCategoryDetailsByCategoryId(categoryId));
    }

    @PostMapping("")
    public ResponseEntity<Category> createNewCategory(@Valid @RequestBody CategoryCreateRequest request) throws ValidationException {
        return ResponseEntity.ok(service.createNewProductCategory(request));
    }

}
