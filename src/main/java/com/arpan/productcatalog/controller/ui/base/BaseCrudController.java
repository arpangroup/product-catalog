package com.arpan.productcatalog.controller.ui.base;

import com.arpan.productcatalog.entity.Student;
import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;


public abstract class BaseCrudController<T, R extends JpaRepository> {
    protected final R repository;
    private final String LIST_VIEW_PAGE = "base/list_view.html";
    private final String RECORDS = "records";
    private final String OBJECT_INFO = "objectInfo";

    private String objectName = "";

    protected BaseCrudController(R repository) {
        this.repository = repository;
    }


//    @GetMapping("/list")
//    protected String findAll(Model model) {
//        List<T> records = repository.findAll();
//        model.addAttribute("modelName", getModelName());
//        model.addAttribute(RECORDS, records);
//        return LIST_VIEW_PAGE;
//    }



    @GetMapping("/api/list")
    protected ObjectListInfo findAllRest(Model model) {
//        List<T> records = repository.findAll();
//        List<Map<String, String>> results = records.stream().map(this::mapTo).collect(Collectors.toList());
//        return results;
        List<T> records = repository.findAll();
        return mapToInfo(records);
    }

    @GetMapping("/list")
    protected String findAll(Model model) {
        List<T> records = repository.findAll();
        ObjectListInfo objectInfo = mapToInfo(records);
        model.addAttribute(OBJECT_INFO, objectInfo);
        return LIST_VIEW_PAGE;
    }


    @GetMapping("")
    protected List<T> getAll() {
        List<Student> students = Arrays.asList(new Student(1l, "John"), new Student(2L, "Bob"));
        return (List<T>) students;
    }

    @GetMapping("/all")
    protected List<T> findAllRecords(Model model) {
        List<T> records = repository.findAll();
        model.addAttribute("records", records);
        return (List<T>) records;
    }

    @GetMapping("/all/{id}")
    protected Optional<T> getAll(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/save")
    public T save(@RequestBody @Valid T t) {
        return (T) repository.save(t);
    }


    protected String getModelName() {
        String arg0 = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();
        return arg0.substring(arg0.lastIndexOf('.') + 1);
    }

    protected List<Map<String, String>> mapTo(List<T> recordList) {
//        List<Map<String, String>> recordMapList = recordList.stream().map(this::mapTo).collect(Collectors.toList());
//        return recordMapList;
        return null;
    }

    protected Map<String, String> mapTo(T t) {
        Map<String, String> valueMap = new LinkedHashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String fName = field.getName();
                Class<?> fieldType = field.getType();
                String fieldVal;

                if (fieldType == Long.class) {
                    Long longVal = (Long) fieldType.cast(field.get(t));
                    fieldVal = longVal.toString();
                } else if (fieldType == Integer.class || fieldType == int.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                } else if (fieldType == String.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                }
                else {
                    fieldVal = null;
                }

                valueMap.put(fName, fieldVal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueMap;
    }


    protected List<Column> mapToFieldInfo(T t) {
        Map<String, String> valueMap = new LinkedHashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String fName = field.getName();
                Class<?> fieldType = field.getType();
                String fieldVal;

                if (fieldType == Long.class) {
                    Long longVal = (Long) fieldType.cast(field.get(t));
                    fieldVal = longVal.toString();
                } else if (fieldType == Integer.class || fieldType == int.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                } else if (fieldType == String.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                    fieldVal = (String) fieldType.cast(field.get(t));
                }
                else {
                    fieldVal = null;
                }

                valueMap.put(fName, fieldVal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return valueMap;
        return getColumns(valueMap);
    }

    public List<Column> getColumns(Map<String, String> fieldsMap) {
        List<Column> columns = fieldsMap.entrySet().stream().map(e -> new Column(e.getKey(), e.getValue())).collect(Collectors.toList());
        return columns;
    }

    private ObjectListInfo mapToInfo(List<T> records) {
        ObjectListInfo objectListInfo = new ObjectListInfo(getModelName());

        for(int i=0; i< records.size(); i ++) {
            List<Column> columns = mapToFieldInfo(records.get(i));
            objectListInfo.addRow(new Row(i+1, columns));
        }
        return objectListInfo;
    }


    /*
    public static<T> Object runGetter(Field field, T t){
        // MZ: Find the correct method
        for (Method method : t.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(t);
                    }
                    catch (IllegalAccessException e)
                    {
                        Logger.fatal("Could not determine method: " + method.getName());
                    }
                    catch (InvocationTargetException e)
                    {
                        Logger.fatal("Could not determine method: " + method.getName());
                    }

                }
            }
        }
        return null;
    }*/

    class Column {
        public String columnName;
        public String value;

        public Column(String columnName, String value) {
            this.columnName = columnName;
            this.value = value;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getValue() {
            return value;
        }
    }

    class Row {
        public int rowId;
        public List<Column> columns = new ArrayList<>();

        public Row(int rowId, List<Column> columns) {
            this.rowId = rowId;
            this.columns = columns;
        }

        public Row addColumn(Column column) {
            this.columns.add(column);
            return this;
        }

        public int getRowId() {
            return rowId;
        }

        public List<Column> getColumns() {
            return columns;
        }
    }


    @Data
    class ObjectListInfo {
        public String objectName;
        public List<String> headers = null;
        public List<Row> rows = new ArrayList<>();

        public ObjectListInfo(String objectName) {
            this.objectName = objectName;
        }

        public ObjectListInfo(String objectName, List<Row> rows) {
            this.objectName = objectName;
            this.rows = rows;
            if(rows != null && rows.size() > 0) {
                this.headers = rows.get(0).getColumns().stream().map(Column::getColumnName).collect(Collectors.toList());
            }
        }

        public ObjectListInfo addRow(Row row) {
            this.rows.add(row);
            if(headers == null) {
                this.headers = row.getColumns().stream().map(Column::getColumnName).collect(Collectors.toList());
            }
            return this;
        }
    }

}