package com.example.demo.DTO;

import com.example.demo.Entity.Gender;
import com.example.demo.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    private Long id;
    private String name;
    private int age;
    private Date DOB;
    private boolean expel;
    private Long classId;

    private Gender gender;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.DOB = student.getDOB();
        this.expel = student.isExpel();
        this.classId = student.getClassroom() != null ? student.getClassroom().getId() : null; // Get class_id
        this.gender = student.getGender();
    }
}