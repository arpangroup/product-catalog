package com.arpan.productcatalog.exception;

public class IdNotFoundException extends BaseValidationException{
    public IdNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public IdNotFoundException(ErrorCode errorCode, String fieldName) {
        super(errorCode, fieldName);
    }
    public IdNotFoundException(String message) {
        super(message);
    }
}

