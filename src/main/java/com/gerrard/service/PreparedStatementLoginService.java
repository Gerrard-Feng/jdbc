package com.gerrard.service;

import com.gerrard.entity.Student;
import com.gerrard.executor.SqlExecutorPreparedStatement;
import com.gerrard.orm.FlexibleResultSetAdapter;
import com.gerrard.orm.ResultSetAdapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class PreparedStatementLoginService implements StudentLoginService {

    private static final String VALIDATE_SQL = "select * from STUDENT s where s.[STUDENT_ID] = ? and s.[STUDENT_PASSWORD] = ?";

    @Override
    public Student login(String id, String password) {
        List<Object> params = new LinkedList<>(Arrays.asList(id, password));
        ResultSetAdapter<Student> adapter = new FlexibleResultSetAdapter<>(Student.class);
        SqlExecutorPreparedStatement<Student> executor = new SqlExecutorPreparedStatement<>(adapter, params);
        List<Student> list = executor.executeQuery(VALIDATE_SQL);
        return list.isEmpty() ? null : list.get(0);
    }
}
