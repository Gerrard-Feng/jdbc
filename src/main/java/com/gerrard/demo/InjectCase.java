package com.gerrard.demo;

import com.gerrard.entity.Student;
import com.gerrard.service.PreparedStatementLoginService;
import com.gerrard.service.StatementLoginService;
import com.gerrard.service.StudentLoginService;

public final class InjectCase {

    public static void main(String[] args) {

        String id1 = "6";
        String pass1 = "123456";

        String id2 = "6";
        String pass2 = "' or 1=1 or '";

        // case 1, normal login with Statement
        StudentLoginService service1 = new StatementLoginService();
        Student student1 = service1.login(id1, pass1);
        if (student1 == null) {
            System.out.println("Login failure.");
        } else {
            System.out.println("Student [" + student1.getName() + "] login success.");
        }

        // case 2, cracker login with Statement
        StudentLoginService service2 = new StatementLoginService();
        Student student2 = service2.login(id2, pass2);
        if (student2 == null) {
            System.out.println("Login failure.");
        } else {
            System.out.println("Student [" + student2.getName() + "] login success.");
        }

        // case 3, normal login with Statement
        StudentLoginService service3 = new PreparedStatementLoginService();
        Student student3 = service3.login(id1, pass1);
        if (student3 == null) {
            System.out.println("Login failure.");
        } else {
            System.out.println("Student [" + student3.getName() + "] login success.");
        }

        // case 4, cracker login with PreparedStatement
        StudentLoginService service4 = new PreparedStatementLoginService();
        Student student4 = service4.login(id2, pass2);
        if (student4 == null) {
            System.out.println("Login failure.");
        } else {
            System.out.println("Student [" + student4.getName() + "] login success.");
        }
    }
}
