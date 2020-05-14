package com.apple.springbootrest.controller;

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

import com.apple.springbootrest.model.Course;
import com.apple.springbootrest.service.CourseService;

@CrossOrigin (origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.findAll();
	}

	@GetMapping("/courses/{id}")
	public Course getCourse(@PathVariable long id) {
		  return courseService.findById(id);
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable long id) {
		Course course = courseService.deleteById(id);
		if (course != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse ( @PathVariable long id,
			                                     @RequestBody Course course) {
		          Course courseUpdated = courseService.save(course);
		          return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PostMapping("/courses")
	public ResponseEntity<Void> createCourse(@RequestBody Course course) {
		         Course createdCourse = courseService.save(course);
		        // Location  // Get current resource url/// {id}
		        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				        .toUri();
		        return ResponseEntity.created(uri).build();
	}
}
