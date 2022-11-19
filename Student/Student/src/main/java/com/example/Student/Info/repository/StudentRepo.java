package com.example.Student.Info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Student.Info.entity.Student;

public interface StudentRepo  extends JpaRepository<Student, Long>  {

}
