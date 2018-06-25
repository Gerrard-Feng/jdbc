package com.gerrard.demo;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class TypicalWrongCase {

    public static void main(String[] args) {
        String sql = "SELECT * from STUDENT";
        dealResultSet(executeQuery(sql));
    }

    public static ResultSet executeQuery(String sql) {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            return rs;
        } catch (SQLException e) {
            String msg = "Fail to execute QUERY using prepared statement.";
            throw new JdbcSampleException(ErrorCode.EXECUTE_QUERY_FAILURE, msg);
        }
    }

    private static void dealResultSet(ResultSet rs) {
        // do something with ResultSet
    }
}
