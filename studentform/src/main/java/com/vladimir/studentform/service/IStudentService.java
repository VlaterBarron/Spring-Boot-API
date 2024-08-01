package com.vladimir.studentform.service;
import com.vladimir.studentform.model.Student;

import java.util.List;

public interface IStudentService {
     public Student addStudent(Student student);
     public List<Student> getAllStudents();
     public Student updateStudentById(int id, Student student);
     public void deleteStudentById(int id);
}
