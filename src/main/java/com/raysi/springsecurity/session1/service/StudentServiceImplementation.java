package com.raysi.springsecurity.session1.service;

import com.raysi.springsecurity.session1.entity.Student;
import com.raysi.springsecurity.session1.error.ResourceNotFoundException;
import com.raysi.springsecurity.session1.repository.StudenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService{

    private final StudenteRepository studenteRepository;

    public StudentServiceImplementation(StudenteRepository studenteRepository){
        this.studenteRepository = studenteRepository;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = studenteRepository.findAll();
        if (students.isEmpty()){
            throw new ResourceNotFoundException("601", "No Students available in the database");
        }
        try{
            return students;
        }catch (RuntimeException e){
            throw new RuntimeException("Something went wrong in Service Layer");
        }
    }

    @Override
    public void saveStudents(List<Student> students) {
        if (students.isEmpty())
            throw new ResourceNotFoundException("701", "Empty Student Can't be Saved!");
        try{
            studenteRepository.saveAll(students);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in the Service Layer");
        }
    }

    @Override
    public void saveStudent(Student student) {
        try{
            studenteRepository.save(student);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in Service layer");
        }
    }
}
