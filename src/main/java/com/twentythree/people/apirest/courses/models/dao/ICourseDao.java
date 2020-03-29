package com.twentythree.people.apirest.courses.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.twentythree.people.apirest.courses.models.entity.Course;

public interface ICourseDao extends CrudRepository<Course, Long>, PagingAndSortingRepository<Course, Long> {
	
	public  List<Course> findAll();
	
	

}
