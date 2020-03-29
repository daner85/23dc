package com.twentythree.people.apirest.courses.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twentythree.people.apirest.courses.exceptions.ResourceNotFoundException;
import com.twentythree.people.apirest.courses.models.entity.Course;
import com.twentythree.people.apirest.courses.models.services.ICourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Api(value="Course Management API")
public class CourseRestController {
	
	
	@Autowired
	private ICourseService courseService;
	
	
	@ApiOperation(value = "View a list of available courses", response = List.class)
	@GetMapping("/courses/all")
	public List<Course> index(){
		return courseService.findAll();
	}
	
	@ApiOperation(value = "View a course detail", response =Course.class)
	@GetMapping("/courses/{id}")
	public Course show(@PathVariable Long id) throws ResourceNotFoundException {
		return courseService.findById(id);
	}
	
	@PostMapping("/courses")
	@ResponseStatus(HttpStatus.CREATED)
	public Course create(@RequestBody Course course) {
		return courseService.save(course);
	}
	
	@PutMapping("/courses/{code}")//Para actualizar un registro
	@ResponseStatus(HttpStatus.CREATED)
	public Course update(@RequestBody Course course, @PathVariable Long code) throws ResourceNotFoundException {
		Course oldCourse = courseService.findById(code);
		oldCourse.setName(course.getName());
		
		return courseService.save(oldCourse);
		
	}
	
	@ApiOperation(value = "Delete a course by code")
	@DeleteMapping("/courses/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException  {
		
		courseService.findById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		courseService.delete(id);
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@RequestMapping(value= {"/courses"}, method=RequestMethod.GET)
	public ResponseEntity<List<Course>> listar(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "code") String sortBy) {
		
		
		List<Course> list = courseService.getAllCourses(pageNo, pageSize, sortBy);
		 
        return new ResponseEntity<List<Course>>(list, new HttpHeaders(), HttpStatus.OK); 

	}
}
