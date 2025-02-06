package com.raysi.springsecurity.session1.repository;

import com.raysi.springsecurity.session1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends JpaRepository<Student, Long> {
}
