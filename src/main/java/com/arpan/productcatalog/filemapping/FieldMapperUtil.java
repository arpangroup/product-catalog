package com.arpan.productcatalog.filemapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldMapperUtil {
    private static final List<String> INTEGER_DATA_TYPES = List.of("int", "java.lang.Integer");
    private static final List<String> BOOLEAN_DATA_TYPES = List.of("boolean", "java.lang.Boolean");
    private static final List<String> CHAR_DATA_TYPES = List.of("char", "java.lang.Character");
    private static final String STRING_DATA_TYPE = "java.lang.String";

    public static final String ANNOTATION_COLUMN_NAME = "jakarta.persistence.Column";
    public static final String ANNOTATION_UI_FIELD_NAME = "com.arpan.productcatalog.filemapping.UiField";

    public static FieldType getFieldType (Field fieldInput) {
        boolean isInteger = INTEGER_DATA_TYPES.contains(fieldInput.getType().getName());
        boolean isBoolean = BOOLEAN_DATA_TYPES.contains(fieldInput.getType().getName());
        boolean isCharacter = CHAR_DATA_TYPES.contains(fieldInput.getType().getName());
        boolean isString = STRING_DATA_TYPE.equals(fieldInput.getType().getName());

        FieldType fieldType;
        if (isInteger) {
            fieldType = FieldType.INPUT_NUMBER;
        } else if (isBoolean) {
            fieldType = FieldType.RADIO;
        } else {
            fieldType = FieldType.INPUT_TEXT;
        }
        return fieldType;
    }

    public static String getLabelName(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1 $2";
        String result = input.replaceAll(regex, replacement).toLowerCase();
        return capitalize(result);
    }

    public static String capitalize(final String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static CustomObjectField mapFieldWithDefaultValues(Field fieldInput, int idSequence) {
        CustomObjectField field = new CustomObjectField().builder()
                .id(idSequence)
                .fieldName(fieldInput.getName())
                .fieldType(FieldMapperUtil.getFieldType(fieldInput).name())
                .labelName(FieldMapperUtil.getLabelName(fieldInput.getName()))
                .build();
        return field;
    }

    public static CustomObjectField constructFromPersistenceColumn(final Annotation annotation, CustomObjectField fieldMap) {
        try {
            jakarta.persistence.Column column = (jakarta.persistence.Column) annotation;
            if (column != null) {

                if(!column.name().isEmpty()) {
                    fieldMap.setFieldName(column.name());
                    fieldMap.setLabelName(getLabelName(column.name()));
                }
                fieldMap.setMandatory(column.nullable());
                fieldMap.setInsertable(column.insertable());
                fieldMap.setUpdatable(column.updatable());
                fieldMap.setLength(column.length());
                fieldMap.setPrecision(column.precision());
                fieldMap.setScale(column.scale());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldMap;
    }


    public static CustomObjectField constructFromUiField(final Annotation annotation, CustomObjectField fieldMap) {
        try {
            UiField uiField = (UiField) annotation;
            if (uiField != null) {
                if(!uiField.fieldName().isEmpty()) {
                    fieldMap.setFieldName(uiField.fieldName());
                    fieldMap.setLabelName(getLabelName(uiField.fieldName()));
                }
                if(!uiField.fieldType().isEmpty()) {
                    fieldMap.setFieldType(uiField.fieldType());
                }

                if(!uiField.labelName().isEmpty()) {
                    fieldMap.setLabelName(uiField.labelName());
                }

                if(!uiField.placeholder().isEmpty()) {
                    fieldMap.setPlaceholder(uiField.placeholder());
                }

                if(!uiField.description().isEmpty()) {
                    fieldMap.setDescription(uiField.description());
                }

                if(uiField.length() != 255) {
                    fieldMap.setLength(uiField.length());
                }

                if(!uiField.defaultValue().isEmpty()) {
                    fieldMap.setDefaultValue(uiField.defaultValue());
                }

                if(!uiField.hoverText().isEmpty()) {
                    fieldMap.setHoverText(uiField.hoverText());
                }

                if(!uiField.errorMessage().isEmpty()) {
                    fieldMap.setErrorMessage(uiField.errorMessage());
                }

                if(uiField.isMandatory()) {
                    fieldMap.setMandatory(true);
                }
                if(!uiField.isInsertable()) {
                    fieldMap.setInsertable(false);
                }
                if(!uiField.isUpdatable()) {
                    fieldMap.setUpdatable(false);
                }
                if(!uiField.isDisabled()) {
                    fieldMap.setDisabled(false);
                }
                if(uiField.isApiCallRequired()) {
                    fieldMap.setApiCallRequired(true);
                }

                if(!uiField.dependentFields().isEmpty()) {
                    fieldMap.setDependentFields(uiField.dependentFields());
                }

                if(!uiField.regex().isEmpty()) {
                    fieldMap.setRegex(uiField.regex());
                }

                if(uiField.precision() != 0) {
                    fieldMap.setPrecision(uiField.precision());
                }

                if(uiField.scale() != 0) {
                    fieldMap.setScale(uiField.scale());
                }

                if(uiField.options().length > 0) {
                    fieldMap.setOptions(Arrays.stream(uiField.options()).toList());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldMap;
    }
}
