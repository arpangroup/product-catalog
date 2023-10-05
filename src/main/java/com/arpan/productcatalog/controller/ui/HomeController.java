package com.arpan.productcatalog.controller.ui;

import com.arpan.productcatalog.controller.ui.base.BaseUiController;
import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.service.StoreService;
import com.arpan.productcatalog.util.PageLayouts;
import com.arpan.productcatalog.util.WebUriConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static com.arpan.productcatalog.util.PageLayouts.*;
import static com.arpan.productcatalog.util.WebUriConstants.*;

@Controller
@AllArgsConstructor
public class HomeController extends BaseUiController {
    private final StoreService storeService;

    @GetMapping(value = {"", "/", "/home", ".dashboard"})
    public String home() {
        if (userInfo.isAdmin()) {
            return PageLayouts.HOME_PAGE;
        } else {
            return "redirect:"+ WebStore_URI;
        }
    }

/*
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

    @GetMapping("/stores")
    public String storeList(Model model, @RequestParam(required = false, defaultValue = "-1") Long catalogId) {
        //model.addAttribute("isStoreFilteredBasedOnCatalog", true);
        List<SimpleStoreResponse> storeList = new ArrayList<>();
        String catalogName = null;
        if (catalogId == -1) {// no catalogId
            storeList = storeService.getAllStore();
        } else {
            var resp = storeService.getAllStoreByCatalogId(catalogId);
            storeList = resp.getStores();
            catalogName = resp.getCatalogName();
            model.addAttribute("catalogId", resp.getCatalogId());
            model.addAttribute("catalogName", catalogName);
        }
        model.addAttribute("stores", storeList);
        return "WebStore_list.html";
    }


    @GetMapping("/stores/new")
    public String getCreateNewStorePage(Model model) {
        model.addAttribute("storeCreateRequest", new StoreCreateRequest());
        return WebStore_NEW_PAGE;
    }

    @PostMapping("/stores/create")
    public RedirectView createNewStore(@ModelAttribute @Valid  StoreCreateRequest request) throws ValidationException {
        storeService.createStore(request);
        //return "redirect:/stores/new";
        return new RedirectView("/stores");
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
 */





}
