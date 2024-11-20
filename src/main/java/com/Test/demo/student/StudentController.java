package com.Test.demo.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // no need to add Autowired, because of constructor here
//    public FirstController(StudentRepository repository, StudentMapper studentMapper) {
//        this.repository = repository;
//        this.studentMapper = studentMapper;
//    }


    @PostMapping("/students")
    public StudentResponceDto saveStudent(
            @Valid @RequestBody StudentDto dto
    ) {
        return this.studentService.saveStudent(dto);
    }



    @GetMapping("/students")
    public List<StudentResponceDto> findAllStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponceDto findStudentById(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponceDto> findStudentsByName(
            @PathVariable("student-name") String name
    ) {
        return studentService.findStudentsByName(name);
    }

    @DeleteMapping("students/{student-id}")
    public ResponseEntity<Student> deleteStudentById(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/students/{student-id}")
    public ResponseEntity<Student> updateStudentById(
            @PathVariable("student-id") Integer id,
            @RequestBody Student updatedStudent
    ) {
        return studentService.updateStudentById(id, updatedStudent);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>(); // 1st will hold the field name and other will hold the msg name
        exp.getBindingResult().getAllErrors()
                .forEach(error ->{
                    var fieldName = ((FieldError) error).getField();
                    var errorMsg = error.getDefaultMessage();
                    errors.put(fieldName, errorMsg);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}