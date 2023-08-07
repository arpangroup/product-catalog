package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.entity.product.ProductCategory;
import com.arpan.productcatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public List<ProductCategory> getAllCategory() {
        return categoryRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<ProductCategory> saveNewCategory(@RequestBody ProductCategory category) {
        return ResponseEntity.ok(categoryRepository.save(category));
    }

}
