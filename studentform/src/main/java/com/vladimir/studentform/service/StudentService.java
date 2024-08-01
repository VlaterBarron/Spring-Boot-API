package com.vladimir.studentform.service;

import com.vladimir.studentform.repository.IStudent;
import com.vladimir.studentform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudent studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudentById(int id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(student.getName());
            existingStudent.setAddress(student.getAddress());
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }
}
