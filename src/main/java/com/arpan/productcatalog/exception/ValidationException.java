package com.arpan.productcatalog.exception;

public class ValidationException extends BaseValidationException{
    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ValidationException(ErrorCode errorCode, String fieldName) {
        super(errorCode, fieldName);
    }

    public ValidationException(String message) {
        super(message);
    }
}

