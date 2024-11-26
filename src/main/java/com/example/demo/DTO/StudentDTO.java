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
    private Long classId; // Or ClassroomDTO classroom;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.classId = student.getClassroom() != null ? student.getClassroom().getId() : null; // Get class_id
    }
}