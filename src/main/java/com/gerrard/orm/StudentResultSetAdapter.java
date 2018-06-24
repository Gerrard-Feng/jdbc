package com.gerrard.orm;

import com.gerrard.constants.ErrorCode;
import com.gerrard.entity.Student;
import com.gerrard.exception.JdbcSampleException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public final class StudentResultSetAdapter implements ResultSetAdapter<Student> {

    @Override
    public Student transferEntity(ResultSet rs, ResultSetMetaData meta) {
        try {
            int id = rs.getInt("STUDENT_ID");
            String name = rs.getString("STUDENT_NAME");
            String password = rs.getString("STUDENT_PASSWORD");
            return new Student(id, name, password);
        } catch (SQLException e) {
            throw new JdbcSampleException(ErrorCode.MISSING_COLUMN_ERROR, "Fail to find column.");
        }
    }
}
