package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.StoreCatalog;
import com.arpan.productcatalog.entity.WebStore;
import com.arpan.productcatalog.exception.IdNotFoundException;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.StoreRepository;
import com.arpan.productcatalog.service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")
@AllArgsConstructor
public class StoreController {
    private final StoreService service;
    private final StoreRepository repository;

    @GetMapping("")
    public ResponseEntity<List<SimpleStoreResponse>> getAllStores() {
        return ResponseEntity.ok(service.getAllStore());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<WebStore> getStoreById(@PathVariable long storeId) throws IdNotFoundException {
        WebStore webStore = repository.findById(storeId).orElseThrow(() -> new IdNotFoundException("Id not found"));


        return ResponseEntity.ok(service.getStoreDetailsById(storeId));
    }

    @PostMapping("")
    public ResponseEntity<WebStore> createNewStore(@Valid @RequestBody StoreCreateRequest request) throws ValidationException {
        return ResponseEntity.ok(service.createStore(request));
    }

}
