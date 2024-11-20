package com.Test.demo.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponceDto saveStudent(
            StudentDto dto
    ) {
        var student = studentMapper.toStudent(dto);
        var savedStudent =  repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent); // to customize what we want to show to user, we use dto, it is like abstraction
    }

    public List<StudentResponceDto> findAllStudent() {
        return repository.findAll()
                .stream() // je pn students ahet tyana convert kr student responce dto madhe
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponceDto findStudentById(Integer id) {
        return repository.findById(id)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponceDto> findStudentsByName(String name) {
        return repository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Student> deleteStudentById(Integer id) {
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Student> updateStudentById(
            Integer id,
            Student updatedStudent
    ) {
        Optional<Student> existingStudent = repository.findById(id);

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();

            // Update fields
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            // Age is set as updatable = false, so it shouldn't be updated

            // Save updated student
            repository.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
