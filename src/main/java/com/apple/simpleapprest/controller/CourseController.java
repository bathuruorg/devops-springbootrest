package com.apple.simpleapprest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apple.simpleapprest.model.Course;
import com.apple.simpleapprest.service.CourseService;

@CrossOrigin (origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	private CourseService courseManagementService;

	@GetMapping("/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		String javahome = System.getenv().getOrDefault("JAVA_HOME", "Hi");
		System.out.println("JAVA_HOME Env Variable From Dockerfile : " + javahome);

		String uname = System.getenv().getOrDefault("USERNAME", "SRINI");
		System.out.println("USERNAME  Env Variable From POD Defination file : " + uname);

		String passwd = System.getenv().getOrDefault("PASSWD", "1234");
		System.out.println("PASSWD    Env Variable From configMap Defination file : " + passwd);

		String dbpasswd = System.getenv().getOrDefault("DBPASSWD", "DB1234");
		System.out.println("DBPASSWD  Env Variable From secret Defination file : " + dbpasswd);
		
		return courseManagementService.findAll();
	}

	@GetMapping("/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
		  return courseManagementService.findById(id);
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {
		Course course = courseManagementService.deleteById(id);
		if (course != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse ( @PathVariable String username,
	                                             @PathVariable long id,
			                                     @RequestBody Course course) {
		          Course courseUpdated = courseManagementService.save(course);
		          return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PostMapping("/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
		         Course createdCourse = courseManagementService.save(course);
		        // Location  // Get current resource url/// {id}
		        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				        .toUri();
		        return ResponseEntity.created(uri).build();
	}
}
