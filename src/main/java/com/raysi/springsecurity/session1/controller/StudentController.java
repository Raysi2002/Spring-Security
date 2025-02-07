package com.raysi.springsecurity.session1.controller;

import com.raysi.springsecurity.session1.entity.Student;
import com.raysi.springsecurity.session1.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> fetchStudents(){
        try{
            List<Student> students = studentService.getStudents();
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header("Accept-Datetime")
                    .body(students);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }

    @PostMapping("/api/students")
    public ResponseEntity<List<Student>> saveStudents(@RequestBody List<Student> students){
        try{
            studentService.saveStudents(students);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .header("Accept-Datetime")
                    .body(students);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }

    @PostMapping("/api/student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        try{
            studentService.saveStudent(student);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Accept-Datetime")
                    .body(student);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw  new RuntimeException("Something went wrong int the Controller layer");
        }
    }

    @GetMapping("/api/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
        try{
            return (CsrfToken) httpServletRequest.getAttribute("_csrf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
