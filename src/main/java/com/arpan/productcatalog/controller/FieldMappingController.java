package com.arpan.productcatalog.controller;

import com.arpan.productcatalog.filemapping.CustomObject;
import com.arpan.productcatalog.filemapping.FieldMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/objects")
@AllArgsConstructor
public class FieldMappingController {
    private FieldMapper fieldMapper;

    @GetMapping
    public List<CustomObject> getObjects() {
        return fieldMapper.mapFieldsV1();
    }
}
