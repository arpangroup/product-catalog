package com.arpan.productcatalog.exception;

public enum ErrorCode {
    ERROR_DUPLICATE_CATEGORY("E001", "Category must be unique");

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
