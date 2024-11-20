package com.Test.demo.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    public final SchoolRepository schoolRepository;
    public final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto createSchool(
            SchoolDto dto
    ) {
        var school = schoolMapper.toSchool(dto);
        var savedSchool =  schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> getSchool() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
