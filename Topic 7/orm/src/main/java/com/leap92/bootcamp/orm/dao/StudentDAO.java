package com.leap92.bootcamp.orm.dao;

import java.util.List;

import com.leap92.bootcamp.orm.entities.Student;

public interface StudentDAO {
	
	public void save (Student s);
	
	public List<Student> fetchAll();
	
	public List<Student> getCourseStudentsById(int id);

}
