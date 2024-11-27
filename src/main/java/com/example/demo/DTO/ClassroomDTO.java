package com.example.demo.DTO;

import com.example.demo.Entity.Classroom;
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
public class ClassroomDTO implements Serializable {
    private Long id;
    private String name;

    public ClassroomDTO(Classroom classroom) {
        this.id = classroom.getId();
        this.name = classroom.getName();
    }
}
