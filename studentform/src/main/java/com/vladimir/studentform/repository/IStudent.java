package com.vladimir.studentform.repository;

import com.vladimir.studentform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent extends JpaRepository<Student, Integer> {
}
