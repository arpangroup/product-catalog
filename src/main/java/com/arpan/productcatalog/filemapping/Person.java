package com.arpan.productcatalog.filemapping;

import jakarta.persistence.Column;

import java.util.List;

@UiObject(objectName = "Person")
public class Person {

    @UiField(fieldName = "personName", fieldType = "INPUT")
    @Column(name="personName")
    public String personName;
    public String username;

    @Column(name="password_column", nullable = false, updatable = false, insertable = false, length = 100, scale = 2)
    public String password;
    public int salary;
    public Integer personAge;

    public boolean isActive;
    public Boolean isAdmin;

    public transient List<String> countris;

}
