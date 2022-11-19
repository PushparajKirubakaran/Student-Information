package com.example.Student.Info.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Student.Info.entity.Student;

@Component
public class StudentDaoService {
	private static List<Student> students = new ArrayList<>();

	private static Long usersCount = 3L;

	static {
		students.add(new Student(1L, "Pushparaj", "K", new Date()));
		students.add(new Student(2L, "Steve", "R", new Date()));
		students.add(new Student(3L, "Jack", "M", new Date()));

	}

	public List<Student> findAll() {
		return students;
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			student.setId(++usersCount);
		}
		students.add(student);
		return student;
	}
	

	public Student findOne(Long id) {
		for (Student students : students) {
			if (students.getId() == id) {
				return students;
			}
		}
		return null;
	}

	public Student deleteById(int id) {
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {

			Student students = iterator.next();
			if (students.getId() == id) {
				iterator.remove();
				return students;
			}
		}

		return null;
	}

}
