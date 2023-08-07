package com.arpan.productcatalog.exception;

public class BaseValidationException extends Exception{
    private String errorCode;
    private String fieldName;

    public BaseValidationException(String message) {
        super(message);
    }

    public BaseValidationException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode.getErrorCode();
        this.fieldName = null;
    }

    public BaseValidationException(ErrorCode errorCode, String fieldName) {
        this(errorCode);
        this.fieldName = fieldName;
    }

    public String getErrorCode() {
        return this.getErrorCode();
    }

    public String getFieldName() {
        return this.getFieldName();
    }

    public String getMessage() {
        return super.getMessage();
    }

}
