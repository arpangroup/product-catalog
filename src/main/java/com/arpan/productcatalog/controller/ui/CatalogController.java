package com.arpan.productcatalog.controller.ui;

import com.arpan.productcatalog.controller.ui.base.BaseUiController;
import com.arpan.productcatalog.dto.CatalogSummary;
import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.StoreCatalog;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.repository.CategoryRepository;
import com.arpan.productcatalog.repository.StoreCatalogRepository;
import com.arpan.productcatalog.repository.StoreRepository;
import com.arpan.productcatalog.service.CatalogService;
import com.arpan.productcatalog.service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

import static com.arpan.productcatalog.util.PageLayouts.*;
import static com.arpan.productcatalog.util.WebUriConstants.*;

@Controller
@RequestMapping("/Catalog")
@AllArgsConstructor
public class CatalogController extends BaseUiController {
    private final CatalogService catalogService;
    private final CatalogRepository catalogRepository;

    @GetMapping("/list")
    public String listView(Model model) {
        List<CatalogSummary> stores = catalogService.getAllCatalogSummary();

        SimpleStoreResponse store = new SimpleStoreResponse();
        store.setId(1L);
        store.setName("Demo Store1");

        model.addAttribute("store", store);
        model.addAttribute("catalogs", stores);
        return Catalog_LIST_PAGE;
    }


    @GetMapping("/{catalogId}/view")
    public String detailView(Model model, @PathVariable Long catalogId) throws IdNotFoundException{
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(() -> new IdNotFoundException("catalogId not found"));
        List<WebStore> stores = catalog.getStores().stream().map(StoreCatalog::getWebStore).collect(Collectors.toList());




        /*List<Category> categories = categoryRepository.findByCatalog_id(catalogId);
        // Category is associated with the Catalog, so to get storeId we have to query to StoreCatalog table first
        List<StoreCatalog> storeCatalogs = storeCatalogRepository.findByCatalog_id(catalogId);
        List<WebStore> stores = storeCatalogs.stream().map(StoreCatalog::getWebStore).collect(Collectors.toList());
         */


        model.addAttribute("catalog_name", catalog.getName());
        model.addAttribute("categories", catalog.getCategories());
        model.addAttribute("stores", stores);

        return Catalog_DETAILS_PAGE;
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("catalog", new Catalog());
        return Catalog_NEW_PAGE;
    }

    @PostMapping("/create")
    public RedirectView createNew(@ModelAttribute @Valid  Catalog request) throws ValidationException {
        catalogService.createNewCatalog(request.getName());
        return new RedirectView(Catalog_LIST_URI);
    }
}
