package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.entity.product.ProductCategory;
import com.arpan.productcatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProductCategory> saveNewCategory(@RequestBody ProductCategory employee) {
        categoryRepository.save(employee);
        return categoryRepository.findAll();
    }

}
