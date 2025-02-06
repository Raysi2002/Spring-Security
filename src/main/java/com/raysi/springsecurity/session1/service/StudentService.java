package com.raysi.springsecurity.session1.service;

import com.raysi.springsecurity.session1.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getStudents();
    void saveStudents(List<Student> students);
    void saveStudent(Student student);
}
