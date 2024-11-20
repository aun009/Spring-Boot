package com.Test.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository represents, <Entity, Id type of entity>

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstNameContaining(String p);

}
