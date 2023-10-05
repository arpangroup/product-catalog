package com.arpan.productcatalog.filemapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UiField {
    String fieldName();
    String fieldType() default "INPUT";
    String labelName() default "";
    String placeholder() default "";
    String description() default "";
    int length() default 255;
    String defaultValue() default "";
    String hoverText() default "";
    String errorMessage() default "";
    boolean isMandatory() default false;
    boolean isInsertable() default true;
    boolean isUpdatable() default true;
    boolean isDisabled() default false;
    boolean isApiCallRequired() default false;
    String dependentFields() default "";
    String regex() default "";
    int precision() default 0;
    int scale() default 0;
    String[] options() default {};
}
