package com.arpan.productcatalog.controller.ui;

import com.arpan.productcatalog.controller.ui.base.BaseUiController;
import com.arpan.productcatalog.dto.request.AssignStoreCatalogRequest;
import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.StoreCatalog;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.exception.ErrorCode;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.mapper.CatalogMapper;
import com.arpan.productcatalog.mapper.StoreMapper;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.repository.StoreCatalogRepository;
import com.arpan.productcatalog.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.arpan.productcatalog.util.PageLayouts.*;
import static com.arpan.productcatalog.util.WebUriConstants.*;

@Controller
@RequestMapping("/StoreCatalog")
public class StoreCatalogController extends BaseUiController {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    StoreCatalogRepository storeCatalogRepository;

    @Autowired
    StoreMapper storeMapper;
    @Autowired
    CatalogMapper catalogMapper;

    @GetMapping("/assign")
    public String getNew(Model model, @RequestParam(value = "storeId", defaultValue = "-1") Long webStoreId, @RequestParam(value = "catalogId", defaultValue = "-1") Long catalogId) throws IdNotFoundException {
        model.addAttribute("storeCreateRequest", new StoreCreateRequest());

        List<WebStore> webStores = webStoreId == -1L ? storeRepository.findAll() : storeRepository.findById(webStoreId).stream().toList();
        List<Catalog> catalogs = catalogId == -1L ? catalogRepository.findAll() : catalogRepository.findById(catalogId).stream().toList();

        AssignStoreCatalogRequest storeCatalogRequest = new AssignStoreCatalogRequest();
        storeCatalogRequest.setStoreId(webStoreId);
        storeCatalogRequest.setCatalogId(catalogId);
        model.addAttribute("storeCatalogRequest", storeCatalogRequest);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("stores", webStores);

        return StoreCatalog_ASSIGN_PAGE;
    }

    @PostMapping("/create")
    public RedirectView createNew(@ModelAttribute  AssignStoreCatalogRequest request) throws ValidationException {
        if (request.getCatalogId() != null && request.getStoreId() != null) {
            WebStore webStore = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new ValidationException(ErrorCode.ERROR_STORE_ID_NOT_FOUND));
            Catalog catalog = catalogRepository.findById(request.getCatalogId()).orElseThrow(() -> new ValidationException(ErrorCode.ERROR_CATALOG_ID_NOT_FOUND));

            StoreCatalog storeCatalog = new StoreCatalog();
            storeCatalog.setWebStore(webStore);
            storeCatalog.setCatalog(catalog);

//            webStore.assignCatalog(storeCatalog);
//            catalog.assignStore(storeCatalog);
            storeCatalogRepository.save(storeCatalog);
        }
        return new RedirectView(WebStore_URI);
    }
}
