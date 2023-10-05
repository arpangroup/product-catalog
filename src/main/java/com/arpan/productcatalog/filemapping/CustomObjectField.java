package com.arpan.productcatalog.filemapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomObjectField {
    private int id;
    private String fieldName;
    private String fieldType = "INPUT_TEXT";
//    private String inputType;
    private String labelName = fieldName;
    private String placeholder = null;
    private String description = null;
    private Integer length = 255;
    private String defaultValue = null;
    private String hoverText = null;
    private String errorMessage = null;
    private boolean isMandatory = false;
    private boolean isInsertable = true;
    private boolean isUpdatable = true;
    private boolean isDisabled = false;
    private boolean isApiCallRequired = false;
    private String dependentFields = null;
    private String regex = null;
    private int precision = 0;
    private int scale = 0;
    private List<String> options = null;

    @Override
    public String toString() {
        return "{" +
                "'id:'" + id +
                ", fieldName:'" + fieldName + '\'' +
                ", fieldType:'" + fieldType + '\'' +
//                ", inputType:'" + inputType + '\'' +
                ", labelName:'" + labelName + '\'' +
                ", placeholder:'" + placeholder + '\'' +
                ", description:'" + description + '\'' +
                ", size:" + length +
                ", defaultValue:'" + defaultValue + '\'' +
                ", hoverText:'" + hoverText + '\'' +
                ", errorMessage:'" + errorMessage + '\'' +
                ", isMandatory:" + isMandatory +
                ", isInsertable:" + isInsertable +
                ", isUpdatable:" + isUpdatable +
                ", isDisabled:" + isDisabled +
                ", isApiCallRequired:" + isApiCallRequired +
                ", dependentFields:'" + dependentFields + '\'' +
                ", regex:'" + regex + '\'' +
                ", precision:'" + precision + '\'' +
                ", scale:'" + scale + '\'' +
                ", options:" + options +
                '}';
    }

}
