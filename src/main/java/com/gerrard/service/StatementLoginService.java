package com.gerrard.service;

import com.gerrard.entity.Student;
import com.gerrard.executor.SqlExecutorStatement;
import com.gerrard.orm.FlexibleResultSetAdapter;
import com.gerrard.orm.ResultSetAdapter;

import java.util.List;

public final class StatementLoginService implements StudentLoginService {

    private static final String VALIDATE_SQL = "select * from STUDENT s where s.[STUDENT_ID] = ? and s.[STUDENT_PASSWORD] = ?";

    @Override
    public Student login(String id, String password) {
        ResultSetAdapter<Student> adapter = new FlexibleResultSetAdapter<>(Student.class);
        SqlExecutorStatement<Student> executor = new SqlExecutorStatement<>(adapter);
        String sql = VALIDATE_SQL.replaceFirst("\\?", id).replaceFirst("\\?", "'" + password + "'");
        List<Student> list = executor.executeQuery(sql);
        return list.isEmpty() ? null : list.get(0);
    }
}
