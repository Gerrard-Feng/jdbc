package com.gerrard.demo;

import com.gerrard.entity.Student;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PreparedStatementDemo {

    public static void main(String[] args) {
        String sql = "INSERT INTO STUDENT (STUDENT_NAME, STUDENT_PASSWORD) VALUES (?, ?)";

        Student student1 = new Student(0, "van Nistelrooy", "666666");
        Student student2 = new Student(0, "van der Sar", "777777");
        List<Student> studentList = new ArrayList<>(Arrays.asList(student1, student2));

        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Student student : studentList) {
                pstmt.setString(1, student.getName());
                pstmt.setObject(2, student.getPassword());
                pstmt.executeUpdate();
            }
            System.out.println("Successfully executeUpdate using PreparedStatement.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
