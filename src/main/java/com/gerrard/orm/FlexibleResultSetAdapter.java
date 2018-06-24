package com.gerrard.orm;

import com.gerrard.annotation.ColumnAnnotation;
import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public final class FlexibleResultSetAdapter<T> implements ResultSetAdapter<T> {

    private Map<String, Field> columnMap = new HashMap<>();

    private Class<T> clazz;

    public FlexibleResultSetAdapter(Class<T> clazz) {
        this.clazz = clazz;
        initColumnMap(clazz);
    }

    private void initColumnMap(Class<T> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            ColumnAnnotation annotation = field.getAnnotation(ColumnAnnotation.class);
            columnMap.put(annotation.column(), field);
        }
    }

    @Override
    public T transferEntity(ResultSet rs, ResultSetMetaData meta) {
        try {
            T t = clazz.newInstance();
            for (int i = 1; i <= meta.getColumnCount(); ++i) {
                String dbColumn = meta.getColumnName(i);
                Field field = columnMap.get(dbColumn);
                if (field == null) {
                    throw new JdbcSampleException(ErrorCode.MISSING_COLUMN_ERROR, "Fail to find column " + dbColumn + ".");
                }
                field.setAccessible(true);
                field.set(t, rs.getObject(i));
            }
            return t;
        } catch (Exception e) {
            String msg = "Fail to get ORM relation for class: " + clazz.getName();
            throw new JdbcSampleException(ErrorCode.MISSING_COLUMN_ERROR, msg);
        }
    }
}
