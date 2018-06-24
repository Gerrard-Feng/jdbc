package com.gerrard.demo;

import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class StatementDemo {

    public static void main(String[] args) {

        String querySql = "SELECT * from STUDENT";
        String updateSql1 = "UPDATE STUDENT SET STUDENT_PASSWORD = '123456' WHERE STUDENT_PASSWORD = '123456'";
        String updateSql2 = "UPDATE STUDENT SET STUDENT_PASSWORD = '223456' WHERE STUDENT_PASSWORD = '223456'";

        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             Statement stmt = conn.createStatement()) {

            // execute
            boolean hasResult = stmt.execute(updateSql1);
            if (hasResult) {
                System.out.println("Execute query success");
            } else {
                System.out.println("Execute update success, affect" + stmt.getUpdateCount() + " rows");
            }

            // executeUpdate
            int rowCount1 = stmt.executeUpdate(updateSql1);
            System.out.println("Affected " + rowCount1 + " rows");

            // executeQuery
            try (ResultSet rs = stmt.executeQuery(querySql)) {
                int counter = 0;
                while (rs.next()) {
                    counter++;
                }
                System.out.println(counter + " students in all");
            }

            // executeBatch
            stmt.addBatch(updateSql1);
            stmt.addBatch(updateSql2);
            stmt.addBatch(updateSql1);
            int[] results = stmt.executeBatch();
            for (int result : results) {
                System.out.println("Affected " + result + " rows");
            }

            // executeLargeUpdate
            stmt.executeLargeUpdate(updateSql1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
