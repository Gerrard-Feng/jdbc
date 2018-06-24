package com.gerrard.entity;

import com.gerrard.annotation.ColumnAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Student {

    @ColumnAnnotation(column = "STUDENT_ID")
    private int id;

    @ColumnAnnotation(column = "STUDENT_NAME")
    private String name;

    @ColumnAnnotation(column = "STUDENT_PASSWORD")
    private String password;
}
