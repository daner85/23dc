package com.twentythree.people.apirest.courses.models.services;

import java.util.List;

import com.twentythree.people.apirest.courses.exceptions.ResourceNotFoundException;
import com.twentythree.people.apirest.courses.models.entity.Course;

public interface ICourseService {
	
	public List<Course> findAll();
	
	public Course save(Course course);
	
	public void delete(Long id);
	
	public Course findById(Long id) throws ResourceNotFoundException;

	public List<Course> getAllCourses(Integer pageNo, Integer pageSize, String sortBy);

}
