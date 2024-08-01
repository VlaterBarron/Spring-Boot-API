package com.vladimir.studentform.controller;

import com.vladimir.studentform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vladimir.studentform.model.Student;

import java.util.List;

@RestController
@RequestMapping("/api/student/v1")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "New student is added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable int id, @RequestBody Student student){
        Student updateStudent = studentService.updateStudentById(id, student);
                return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
        studentService.deleteStudentById(id);
        return "Student ID:  " + id + " Was successfully deleted";
    }

}
