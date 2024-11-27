package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="classes")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "classroom")
    private Set<Student> students; // This establishes the relationship
}
