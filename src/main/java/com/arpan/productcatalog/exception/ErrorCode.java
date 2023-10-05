package com.arpan.productcatalog.exception;

public enum ErrorCode {
    ERROR_DUPLICATE_CATEGORY("E001", "Category must be unique"),
    ERROR_CATEGORY_ID_NOT_FOUND("E002", "categoryId not found"),

    // STORE
    ERROR_STORE_ID_NOT_FOUND("STORE001", "storeId not found"),
    ERROR_STORE_NAME_NOT_FOUND("STORE002", "storeName not found"),
    ERROR_STORE_EXTERNAL_REFERENCE_ID_NOT_FOUND("STORE003", "externalReferenceId not found"),
    ERROR_DUPLICATE_STORE_NAME("STORE004", "storeName already exist"),

    // CATALOG
    ERROR_CATALOG_ID_NOT_FOUND("CATALOG001", "catalogID not found"),
    ERROR_CATALOG_NAME_SHOULD_NOT_BE_NULL("CATALOG002", "catalogName should not be null"),
    ERROR_CATALOG_NAME_INVALID_LENGTH("CATALOG003", "catalogName should be minimum 4 characters long and maximum 50 characters long"),
    ERROR_DUPLICATE_CATALOG("CATALOG004", "catalogName already exist for this store");

    private final String errorCode;
    private final String description;


    ErrorCode(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return this.description;
    }
}
