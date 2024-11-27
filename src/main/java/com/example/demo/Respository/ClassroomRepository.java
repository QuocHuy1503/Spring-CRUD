package com.example.demo.Respository;

import com.example.demo.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom c WHERE c.name = :name")
    Optional<Classroom> findByName(@Param("name") String name);

    @Override
    Optional<Classroom> findById(Long aLong);

}
