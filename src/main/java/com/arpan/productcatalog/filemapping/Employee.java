package com.arpan.productcatalog.filemapping;

@UiObject(objectName = "Employee")
public class Employee {

    @UiField(fieldName = "employeeName", fieldType = "INPUT")
    public String employeeName;
    public String username;
    public String password;
    public int salary;
    public int employeeAge;
}
