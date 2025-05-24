// src/main/java/com/library/repository/StudentRepository.java
package com.library.management.repository;

import com.library.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByOrderByFirstNameAsc();
}