package com.Test.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void should_successfully_save_student() {
        //Given
        StudentDto dto = new StudentDto(
                "Jack",
                "Sparrow",
                "jack2232@gmail.com",
                2
        );

        Student student = new Student(
                "Jack",
                "Sparrow",
                "jack2232@gmail.com",
                20
        );

        //When
        StudentResponceDto responceDto = studentService.saveStudent(dto);

        //Then
    }
}