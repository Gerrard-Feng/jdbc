package com.gerrard.demo;

import com.gerrard.entity.Student;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ResultSetDemo {

    public static void main(String[] args) {
        String sql = "SELECT * from STUDENT";

        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            dealResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dealResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            Student student = new Student(id, name, password);
            System.out.println(student);
        }
    }
}
