package com.Test.demo.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("Jack", "Sparrow", "jack2232@gmail.com", 2);

        Student student = mapper.toStudent(dto);


        Assertions.assertEquals(dto.firstName(), student.getFirstName());
        Assertions.assertEquals(dto.lastName(), student.getLastName());
        Assertions.assertEquals(dto.email(), student.getEmail());
        Assertions.assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    @Test
    public void shouldMapStudentToStudentResponceDto() {
        Student student = new Student("Ak", "Mafia", "aj12@gmail.com", 22);

        StudentResponceDto dto = mapper.toStudentResponseDto(student);

        Assertions.assertEquals(student.getFirstName(), dto.firstName());
        Assertions.assertEquals(student.getLastName(), dto.lastName());
        Assertions.assertEquals(student.getEmail(), dto.email());

    }

    @Test
    public void should_throw_NullPointer_exception_when_studentDto_is_null() {
        Assertions.assertThrows(NullPointerException.class, ()-> mapper.toStudent(null));
    }

}