package com.example.demo.Respository;

import com.example.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Override
    Optional<Student> findById(Long aLong);

    @Query("SELECT s FROM Student s WHERE s.classroom.id = :classId")
    List<Student> findByClassroomId(@Param("classId") Long classId);
}
