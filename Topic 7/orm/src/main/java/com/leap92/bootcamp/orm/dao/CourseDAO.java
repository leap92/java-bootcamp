package com.leap92.bootcamp.orm.dao;

import java.util.List;

import com.leap92.bootcamp.orm.entities.Course;

public interface CourseDAO {
	
	public void save (Course c);
	
	public List<Course> fetchAll ();
	
	public List<Course> getStudentCoursesById(int id);

}
