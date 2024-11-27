package com.example.demo.Controller;

import com.example.demo.DTO.ClassroomDTO;
import com.example.demo.Service.ClassroomService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<Page<ClassroomDTO>> getClassrooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ClassroomDTO> classrooms = classroomService.getClassrooms(page, size);
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroom(@PathVariable Long id) {
        ClassroomDTO classroomDTO = classroomService.getClassroom(id);
        if (classroomDTO != null) {
            return new ResponseEntity<>(classroomDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO) {
        ClassroomDTO createdClassroom = classroomService.createClassRoom(classroomDTO);
        return new ResponseEntity<>(createdClassroom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(
            @PathVariable Long id,
            @RequestBody ClassroomDTO classroomDTO) {
        ClassroomDTO updatedClassroom = classroomService.updateClassroom(id, classroomDTO);
        if (updatedClassroom != null) {
            return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        try {
            classroomService.deleteClassroom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle case where student is not found
        }
    }

}