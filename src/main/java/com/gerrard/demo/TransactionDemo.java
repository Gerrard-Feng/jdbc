package com.gerrard.demo;

import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class TransactionDemo {

    public static void main(String[] args) {

        String sql1 = "INSERT INTO STUDENT (STUDENT_NAME, STUDENT_PASSWORD) VALUES ('Ramsey', '999999')";
        // Cause primary key problem
        String sql2 = "INSERT INTO STUDENT VALUES(1, 'Ramsey', '999999')";

        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection()) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
