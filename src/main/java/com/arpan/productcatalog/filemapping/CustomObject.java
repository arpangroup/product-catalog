package com.arpan.productcatalog.filemapping;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomObject {
    private String objectName;
    private List<CustomObjectField> fields;

    public CustomObject(String objectName) {
        this.objectName = objectName;
        this.fields = new ArrayList<>();
    }

    public CustomObject(String objectName, List<CustomObjectField> fields) {
        this.objectName = objectName;
        this.fields = fields != null ? fields : new ArrayList<>();
    }

    public void addField(CustomObjectField field) {
        fields.add(field);
    }


    @Override
    public String toString() {
        return "{" +
                "objectName:'" + objectName + '\'' +
                ", fields:" + fields +
                '}';
    }
}
