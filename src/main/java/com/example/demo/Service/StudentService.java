package com.example.demo.Service;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Classroom;
import com.example.demo.Entity.Student;
import com.example.demo.Respository.ClassroomRepository; // Fixed typo in package name
import com.example.demo.Respository.StudentRepository; // Fixed typo in package name
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final com.example.demo.Respository.StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository; // Added repository field

    @Autowired
    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository; // Initialize the repository
    }

    public Page<StudentDTO> getStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(StudentDTO::new); // Use method reference for cleaner code
    }

    public StudentDTO getStudent(Long id) {
        return studentRepository.findById(id).map(StudentDTO::new).orElse(null);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        // Fetch the Classroom entity based on classId
        if (studentDTO.getClassId() != null) {
            Optional<Classroom> classroomOpt = classroomRepository.findById(studentDTO.getClassId());
            if (classroomOpt.isPresent()) {
                student.setClassroom(classroomOpt.get()); // Set the classroom if found
            } else {
                // Handle case where classroom is not found (throw exception or return null)
                throw new RuntimeException("Classroom not found with id: " + studentDTO.getClassId());
            }
        }
        student = studentRepository.save(student);
        return new StudentDTO(student);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null; // Or throw an exception
        }
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge()); // Update age if provided

        // Update classroom if classId is provided
        if (studentDTO.getClassId() != null) {
            Optional<Classroom> classroomOpt = classroomRepository.findById(studentDTO.getClassId());
            if (classroomOpt.isPresent()) {
                student.setClassroom(classroomOpt.get());
            } else {
                throw new RuntimeException("Classroom not found with id: " + studentDTO.getClassId());
            }
        }

        student = studentRepository.save(student);
        return new StudentDTO(student);
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

//    public List<StudentDTO> getStudentsByClassroomId(Long classId) {
//        List<Student> students = studentRepository.findByClassroomId(classId);
//        return students.stream().map(StudentDTO::new).collect(Collectors.toList());
//    }

}