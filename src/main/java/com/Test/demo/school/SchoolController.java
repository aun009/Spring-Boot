package com.Test.demo.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto
    ) {
        return schoolService.createSchool(dto);
    }



    // in school there it gets list of students and in students it gets school, this goes in infinite loop to fix it use jaxon annotation
    // @JsonManagedReference use this on parent
    @GetMapping("/schools")
    public List<SchoolDto> getSchool() {
        return schoolService.getSchool();
    }




}
