package com.arpan.productcatalog.filemapping;

import com.arpan.productcatalog.entity.WebStore;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class FieldMapper {
    public static List<String> mapFields() {
//        Reflections reflections = new Reflections("com.baeldung.reflections");
        Reflections reflections = new Reflections(WebStore.class);
//        Reflections reflections = new Reflections(MyClass.class.getClassLoader());


        /*Class<?> uiObjectClasses = ClassUtils.getUserClass(Person.class);

        List<String> annotateMethods = Arrays.stream(uiObjectClasses.getMethods())
                .filter(method -> AnnotationUtils.getAnnotation(method, UiField.class) != null)
                .map(method -> method.getAnnotation(UiField.class))
                .map(field -> "{" + field.fieldName() + ": " + field.fieldType() + "}")
                .collect(Collectors.toList());

        List<String> annotateFields = Arrays.stream(uiObjectClasses.getFields())
                .filter(field -> AnnotationUtils.getAnnotation(field, UiField.class) != null)
                .map(field -> field.getAnnotation(UiField.class))
                .map(field -> "{" + field.fieldName() + ": " + field.fieldType() + "}")
                .collect(Collectors.toList());

        List<String> fields = new ArrayList<>();
        fields.addAll(annotateMethods);
        fields.addAll(annotateFields);
        return fields;*/
        return null;

    }

    public List<CustomObject> mapFieldsV1() {
        Reflections reflections = new Reflections("com.arpan.productcatalog.*");
        Set<Class<?>> allClass = reflections.getTypesAnnotatedWith(UiObject.class);
        List<CustomObject> customObjects = new ArrayList<>();

        for(Class<?> selectedClass : allClass) {
            String fullClassName = selectedClass.getName();
            String packageName = selectedClass.getPackageName();
            String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
            //System.out.println("CLASS_NAME: " + className);
            CustomObject customObject = new CustomObject(className);

            Field[] fields = selectedClass.getFields();
            int idSequence = 0;
            for(Field field : fields) {
                // Step1: Set the default values from fieldName
                CustomObjectField fieldMap = FieldMapperUtil.mapFieldWithDefaultValues(field, ++idSequence);

                // Step2: Process the annotations
                fieldMap = processAnnotations(field, fieldMap);

                //System.out.println(fieldMap);
                customObject.addField(fieldMap);
            }
            customObjects.add(customObject);
        }

        //System.out.println(customObjects);
        return customObjects;
    }

    private static CustomObjectField processAnnotations(Field field, CustomObjectField fieldMap) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        Annotation columnAnnotation = Arrays.stream(annotations).filter(a -> a.annotationType().getName().equals(FieldMapperUtil.ANNOTATION_COLUMN_NAME)).findFirst().orElse(null);
        Annotation uiFieldAnnotation = Arrays.stream(annotations).filter(a -> a.annotationType().getName().equals(FieldMapperUtil.ANNOTATION_UI_FIELD_NAME)).findFirst().orElse(null);

        // Step 2.1: priority1: Process the Column annotation
        if (columnAnnotation != null) {
            FieldMapperUtil.constructFromPersistenceColumn(columnAnnotation, fieldMap);
        }

        // Step 2.2: priority2: Process our own UiField annotation
        if (uiFieldAnnotation != null) {
            FieldMapperUtil.constructFromUiField(uiFieldAnnotation, fieldMap);
        }
        return fieldMap;
    }


    public void print() {
        List<CustomObject> fields = mapFieldsV1();
        System.out.println(fields);
    }
}
