package com.gerrard.executor;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;
import com.gerrard.orm.ResultSetAdapter;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public final class SqlExecutorPreparedStatement<T> implements SqlExecutor<T> {

    private ResultSetAdapter<T> adapter;
    private List<Object> params = new LinkedList<>();

    @Override
    public int executeUpdate(String sql) {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int counter = 1;
            for (Object param : params) {
                pstmt.setObject(counter++, param);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            String msg = "Fail to execute INSERT/DELETE/UPDATE using prepared statement.";
            throw new JdbcSampleException(ErrorCode.EXECUTE_QUERY_FAILURE, msg);
        }
    }

    public List<T> executeQuery(String sql) {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int counter = 1;
            for (Object param : params) {
                pstmt.setObject(counter++, param);
            }
            List<T> list = new LinkedList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(adapter.transferEntity(rs, rs.getMetaData()));
                }
            }
            return list;
        } catch (SQLException e) {
            String msg = "Fail to execute QUERY using prepared statement.";
            throw new JdbcSampleException(ErrorCode.EXECUTE_QUERY_FAILURE, msg);
        }
    }
}
