package com.example.demo.Respository;

import com.example.demo.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Override
    Optional<Classroom> findById(Long aLong);

}
