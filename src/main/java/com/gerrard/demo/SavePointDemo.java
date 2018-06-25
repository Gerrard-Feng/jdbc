package com.gerrard.demo;

import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public final class SavePointDemo {

    public static void main(String[] args) {
        String sql1 = "INSERT INTO STUDENT (STUDENT_NAME, STUDENT_PASSWORD) VALUES ('Ramsey', '999999')";
        String sql2 = "INSERT INTO STUDENT (STUDENT_NAME, STUDENT_PASSWORD) VALUES ('Wilshere', '888888')";
        // Invalidate SQL
        String sql3 = "INSERT INTO STUDENT VALUES(1, 'Ramsey', '999999')";

        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection()) {
            conn.setAutoCommit(false);
            Savepoint save1 = null;
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql1);
                save1 = conn.setSavepoint();
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);
            } catch (SQLException e) {
                conn.rollback(save1);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
