package com.gerrard.service;

import com.gerrard.entity.Student;

public interface StudentLoginService {

    Student login(String id, String password);
}
