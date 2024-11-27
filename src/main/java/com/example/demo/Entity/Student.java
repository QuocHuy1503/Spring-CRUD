package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classroom classroom; // This establishes the relationship

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender; // Enum for gender

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "guardian_name", length = 50)
    private String guardianName;

    @Column(name = "guardian_phone_number", length = 20)
    private String guardianPhoneNumber;

    @Column(name = "is_expel")
    private boolean isExpel; // Boolean field for expulsion status

    // Enum for gender
    public enum Gender {
        Male,
        Female,
        Other
    }
}