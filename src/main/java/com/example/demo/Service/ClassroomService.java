package com.example.demo.Service;

import com.example.demo.DTO.ClassroomDTO;
import com.example.demo.Entity.Classroom;
import com.example.demo.Respository.ClassroomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository ){
        this.classroomRepository = classroomRepository;
    }

    public Page<ClassroomDTO> getClassrooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Classroom> classrooms = classroomRepository.findAll(pageable);
        return classrooms.map(classroom -> {
            ClassroomDTO ClassroomDTO = new ClassroomDTO(classroom);
            ClassroomDTO.setId(classroom.getId()); // Set the student_id explicitly
            return ClassroomDTO;
        });
    }

    public ClassroomDTO getClassroom(Long id){
        return classroomRepository.findById(id).map(ClassroomDTO::new).orElse(null);
    }

    public ClassroomDTO createClassRoom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomDTO.getName());
        classroom = classroomRepository.save(classroom);
        ClassroomDTO classroomDTO1 = new ClassroomDTO(classroom);
        classroomDTO1.setId(classroom.getId()); // Set the student_id explicitly
        return classroomDTO1;
    }

    public ClassroomDTO updateClassroom(Long id, ClassroomDTO classroomDTO) {
        Classroom classroom = classroomRepository.findById(id).orElse(null);
        if (classroom == null) {
            return null;
        }
        classroom.setName(classroomDTO.getName());
        classroom = classroomRepository.save(classroom);
        ClassroomDTO classroomDTOUpdated = new ClassroomDTO(classroom);
        classroomDTOUpdated.setId(classroom.getId()); // Set the student_id explicitly
        return classroomDTOUpdated;
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}
