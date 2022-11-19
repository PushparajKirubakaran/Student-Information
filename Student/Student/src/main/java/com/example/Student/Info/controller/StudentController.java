package com.example.Student.Info.controller;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.Student.Info.customAnnotation.LogRequest;
import com.example.Student.Info.entity.Student;
import com.example.Student.Info.exception.UserNotFoundException;
import com.example.Student.Info.repository.StudentRepo;
import com.example.Student.Info.service.StudentDaoService;

@RestController
public class StudentController {

	@Autowired
	private StudentRepo repository;

	@Autowired
	private StudentDaoService service;

	@GetMapping("/students")
	public List<Student> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/students/{id}")
	public Student findStudentById(@PathVariable Long id) {
		Student student = service.findOne(id);

		if (student == null) {
			throw new UserNotFoundException("id-" + id);

		} else {
			return student;
		}

	}

	@DeleteMapping("/students/{id}")
	public void deleteUser(@PathVariable int id) {
		Student students = service.deleteById(id);

		if (students == null)
			throw new UserNotFoundException("id-" + id);

	}
	@LogRequest(enabled = false)
	@PostMapping("/students")
	public ResponseEntity<Student> create(@RequestBody Student newstudent) throws ServerException {

		Student student = service.save(newstudent);
		if (student == null) {
			throw new ServerException("User id is Null");
		} else {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
	}

	@LogRequest(enabled = true)
	@PostMapping("/studentsLog")
	public ResponseEntity<Student> createStudent(@RequestBody Student newstudent) throws ServerException {

		Student student = service.save(newstudent);
		if (student == null) {
			throw new ServerException("User id is Null");
		} else {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
	}

}
