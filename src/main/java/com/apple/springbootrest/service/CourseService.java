package com.apple.springbootrest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apple.springbootrest.model.Course;

@Service
public class CourseService {

	private static List<Course> courses = new ArrayList<>();
	private static long idCounter = 0;

	static {
		courses.add(new Course(++idCounter, "in28minutes", "Learn Full stack with Spring Boot and Angular"));
		courses.add(new Course(++idCounter, "in28minutes", "Learn Full stack with Spring Boot and React"));
		courses.add(new Course(++idCounter, "in28minutes", "Master Microservices with Spring Boot and Spring Cloud"));
		courses.add(new Course(++idCounter, "in28minutes",
				"Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
	}

	public List<Course> findAll() {
		String javahome = System.getenv().getOrDefault("JAVA_HOME", "Hi");
		System.out.println("JAVA_HOME Env Variable From Dockerfile : " + javahome);

		String uname = System.getenv().getOrDefault("USERNAME", "SRINI");
		System.out.println("USERNAME  Env Variable From POD Defination file : " + uname);

		String passwd = System.getenv().getOrDefault("PASSWD", "1234");
		System.out.println("PASSWD    Env Variable From configMap Defination file : " + passwd);

		String dbpasswd = System.getenv().getOrDefault("DBPASSWD", "DB1234");
		System.out.println("DBPASSWD  Env Variable From secret Defination file : " + dbpasswd);
		return courses;
	}

	public Course save(Course course) {
		if (course.getId() == -1 || course.getId() == 0) {
			course.setId(++idCounter);
			courses.add(course);
		} else {
			deleteById(course.getId());
			courses.add(course);
		}
		return course;
	}

	public Course deleteById(long id) {
		Course course = findById(id);

		if (course == null)
			return null;

		if (courses.remove(course)) {
			return course;
		}

		return null;
	}

	public Course findById(long id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}

		return null;
	}
}
