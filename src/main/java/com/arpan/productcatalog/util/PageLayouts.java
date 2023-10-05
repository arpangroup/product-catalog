package com.arpan.productcatalog.util;

import org.springframework.stereotype.Component;

@Component
public class PageLayouts {
    private static final String WebStore_Resource_Directory = "/WebStore/WebStore_";
    private static final String StoreCatalog_Resource_Directory = "/StoreCatalog/StoreCatalog_";
    private static final String Catalog_Resource_Directory = "/Catalog/Catalog_";

    public static final String HOME_PAGE = "dashboard.html";

    public static final String WebStore_NEW_PAGE     = WebStore_Resource_Directory + "new.html";
    public static final String WebStore_LIST_PAGE    = WebStore_Resource_Directory + "list.html";
    public static final String WebStore_DETAILS_PAGE = WebStore_Resource_Directory + "view.html";

    public static final String StoreCatalog_ASSIGN_PAGE  = StoreCatalog_Resource_Directory + "assign.html";
    public static final String StoreCatalog_LIST_PAGE    = StoreCatalog_Resource_Directory + "list.html";
    public static final String StoreCatalog_DETAILS_PAGE = StoreCatalog_Resource_Directory + "view.html";

    public static final String Catalog_NEW_PAGE     = Catalog_Resource_Directory + "new.html";
    public static final String Catalog_LIST_PAGE    = Catalog_Resource_Directory + "list.html";
    public static final String Catalog_DETAILS_PAGE = Catalog_Resource_Directory + "view.html";
}
