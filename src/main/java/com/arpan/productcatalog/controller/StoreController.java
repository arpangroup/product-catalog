package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.dto.response.SimpleStoreResponse;
import com.arpan.productcatalog.entity.Store;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@AllArgsConstructor
public class StoreController {
    private final StoreService service;

    @GetMapping("")
    public ResponseEntity<List<SimpleStoreResponse>> getAllStores() {
        return ResponseEntity.ok(service.getAllStore());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreById(@PathVariable long storeId) throws ValidationException {
        return ResponseEntity.ok(service.getStoreDetailsById(storeId));
    }

    @PostMapping("")
    public ResponseEntity<Store> createNewStore(@Valid @RequestBody StoreCreateRequest request) throws ValidationException {
        return ResponseEntity.ok(service.createStore(request));
    }

}
