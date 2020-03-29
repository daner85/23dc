package com.twentythree.people.apirest.courses.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twentythree.people.apirest.courses.exceptions.ResourceNotFoundException;
import com.twentythree.people.apirest.courses.models.dao.ICourseDao;
import com.twentythree.people.apirest.courses.models.entity.Course;

@Service
public class CourseServiceImpl implements ICourseService{
	
	@Autowired
	private ICourseDao courseDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Course> findAll() {
		return (List<Course>) courseDao.findAll();
	}
	
	public List<Course> getAllCourses(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Course> pagedResult = courseDao.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Course>();
        }
    }

	@Override
	@Transactional
	public Course save(Course course) {
		return courseDao.save(course);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		courseDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Course findById(Long id) throws ResourceNotFoundException {
		return courseDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + id));
	}

}
