package com.example.demo.DTO;

import com.example.demo.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    private Long id;
    private String name;
    private int age;

    public StudentDTO(Student student) {
        this.age = student.getAge();
        this.name = student.getName();
    }
}