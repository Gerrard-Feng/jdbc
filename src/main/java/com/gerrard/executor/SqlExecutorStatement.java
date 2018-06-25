package com.gerrard.executor;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;
import com.gerrard.orm.ResultSetAdapter;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public final class SqlExecutorStatement<T> implements SqlExecutor<T> {

    private ResultSetAdapter<T> adapter;

    @Override
    public int executeUpdate(String sql) {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            String msg = "Fail to execute query using statement.";
            throw new JdbcSampleException(ErrorCode.EXECUTE_UPDATE_FAILURE, msg);
        }
    }

    @Override
    public List<T> executeQuery(String sql) {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             Statement stmt = conn.createStatement()) {
            List<T> list = new LinkedList<>();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(adapter.transferEntity(rs, rs.getMetaData()));
                }
            }
            return list;
        } catch (SQLException e) {
            String msg = "Fail to execute query using statement.";
            throw new JdbcSampleException(ErrorCode.EXECUTE_QUERY_FAILURE, msg);
        }
    }
}
