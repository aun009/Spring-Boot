package com.Test.demo.student;

import com.Test.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponceDto toStudentResponseDto(Student student) {

        return new StudentResponceDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public Student toStudent(StudentDto dto) {

        if(dto == null) throw new NullPointerException("The Student DTO is null");
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }
}
