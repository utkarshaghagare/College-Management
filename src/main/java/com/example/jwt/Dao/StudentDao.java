package com.example.jwt.Dao;

import com.example.jwt.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, String> {

}
