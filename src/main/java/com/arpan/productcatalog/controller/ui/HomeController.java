package com.arpan.productcatalog.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping(value = {"", "/", "/home", ".dashboard"})
    public String home() {
        return "dashboard.html";
    }


    @GetMapping("/StoreCategories")
    public String storeCategories() {
        return "StoreCategories.html";
    }

    @GetMapping("/ProductCategoryDetails/{id}")
    public String productCategory(@PathVariable String id) {
        return "ProductCategoryDetails.html";
    }

    @GetMapping("/CreateCategory")
    public String createCategory() {
        return "CreateCategory.html";
    }

    @GetMapping("/CreateStoreCatalog")
    public String createStoreCatalog() {
        return "CreateStoreCatalog.html";
    }

    @GetMapping("/CategoryList")
    public String categoryList() {
        return "CategoryList.html";
    }

    @GetMapping("/StoreList")
    public String storeList() {
        return "StoreList.html";
    }

    @GetMapping("/ProductList")
    public String productList() {
        return "ProductList.html";
    }

    @GetMapping("/ProductDetails/{id}")
    public String productDetails(@PathVariable String id) {
        return "ProductDetails.html";
    }

    @GetMapping("/CreateProduct")
    public String createProduct() {
        return "CreateProduct.html";
    }



}
