package com.arpan.productcatalog.controller.ui;

import com.arpan.productcatalog.config.UserInfoHolder;
import com.arpan.productcatalog.controller.ui.base.BaseUiController;
import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.StoreRepository;
import com.arpan.productcatalog.service.StoreService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpRequest;
import java.util.List;

import static com.arpan.productcatalog.util.PageLayouts.*;
import static com.arpan.productcatalog.util.WebUriConstants.*;

@Controller
@RequestMapping("/WebStore")
@AllArgsConstructor
public class WebStoreController extends BaseUiController {
    private final StoreService storeService;
    private final StoreRepository repository;

    @RolesAllowed({""})
    @GetMapping("")
    public RedirectView home(RedirectView redirectView) {
//        List<SimpleStoreResponse> stores = storeService.getAllStore();
        List<WebStore> stores = repository.findByCreatedBy("arpan");
        //redirectView.setUrl(stores.size() == 0 ? URI_WebStore_NEW : URI_WebStore_LIST);
        if (stores.size() == 1) {
            redirectView.setContextRelative(true);
            redirectView.setUrl(WebStore_DETAILS_URI);
            redirectView.addStaticAttribute("storeId", stores.get(0).getId());
            return redirectView;
        }
        if (stores.size() > 1) {
            redirectView.setUrl(WebStore_LIST_URI);
        } else {
            redirectView.setUrl(WebStore_NEW_URI);
        }
        return redirectView;
    }


    @GetMapping("/list")
    public String listView(Model model) {
//        List<SimpleStoreResponse> stores = storeService.getAllStore();
        List<WebStore> stores = repository.findByCreatedBy("admin");
        model.addAttribute("stores", stores);
        return WebStore_LIST_PAGE;
    }


    @GetMapping("/{storeId}/view")
    public String detailView(Model model, @PathVariable Long storeId) throws IdNotFoundException{
        WebStore webStore = storeService.getStoreDetailsById(storeId);
        model.addAttribute("webStore", webStore);

        model.addAttribute("isCatalogAssigned", webStore.getCatalogs().size() > 0);
        model.addAttribute("isBuyerGroupAssigned", false);
        model.addAttribute("isPriceBookAssigned", false);
        model.addAttribute("isSearchIndexExist", false);
        model.addAttribute("isReadyToActivate", false);

        return WebStore_DETAILS_PAGE;
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("storeCreateRequest", new StoreCreateRequest());
        return WebStore_NEW_PAGE;
    }

    @PostMapping("/create")
    public RedirectView createNew(@ModelAttribute @Valid  StoreCreateRequest request) throws ValidationException {
        WebStore webStore = storeService.createStore(request);
        return new RedirectView(WebStore_URI);
    }
}
