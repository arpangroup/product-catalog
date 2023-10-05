package com.arpan.productcatalog.util;

import org.springframework.stereotype.Component;

@Component
public class WebUriConstants {
    public static final String WebStore_URI         = "/WebStore";
    public static final String WebStore_NEW_URI     = WebStore_URI + "/new";
    public static final String WebStore_LIST_URI    = WebStore_URI + "/list";
    public static final String WebStore_DETAILS_URI = WebStore_URI + "/{storeId}/view";

    public static final String StoreCatalog_URI         = "/StoreCatalog";
    public static final String StoreCatalog_NEW_URI     = StoreCatalog_URI + "/assign";
    public static final String StoreCatalog_LIST_URI    = StoreCatalog_URI + "/list";
    public static final String StoreCatalog_DETAILS_URI = StoreCatalog_URI + "/{storeCatalogId}/view";

    public static final String Catalog_URI          = "/Catalog";
    public static final String Catalog_NEW_URI      = Catalog_URI + "/new";
    public static final String Catalog_LIST_URI     = Catalog_URI + "/list";
    public static final String Catalog_DETAILS_URI  = Catalog_URI + "/{catalogId}/view";

    public static final String CATEGORY_URI         = "/Category";
    public static final String CATEGORY_NEW_URI     = CATEGORY_URI + "/new";
    public static final String CATEGORY_LIST_URI    = CATEGORY_URI + "/list";
    public static final String CATEGORY_DETAILS_URI = CATEGORY_URI + "/{categoryId}/view";

    public static final String PRODUCT_URI          = "/Product";
    public static final String PRODUCT_NEW_URI      = PRODUCT_URI + "/new";
    public static final String PRODUCT_LIST_URI     = PRODUCT_URI + "/list";
    public static final String PRODUCT_DETAILS_URI  = PRODUCT_URI + "/{productId}/view";

    public static final String PRICING_URI          = "/Price";
    public static final String PRICING_NEW_URI      = PRICING_URI + "/new";
    public static final String PRICING_LIST_URI     = PRICING_URI + "/list";
    public static final String PRICING_DETAILS_URI  = PRICING_URI + "/{priceId}/view";

    public static final String PROMOTION_URI        = "/Promotion";
    public static final String PROMOTION_NEW_URI    = PROMOTION_URI + "/new";
    public static final String PROMOTION_LIST_URI   = PROMOTION_URI + "/list";
    public static final String PROMOTION_DETAILS_URI= PROMOTION_URI + "/{promotionId}/view";
}
