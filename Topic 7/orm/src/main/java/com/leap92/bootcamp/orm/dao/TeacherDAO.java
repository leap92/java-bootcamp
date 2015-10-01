package com.leap92.bootcamp.orm.dao;

import java.util.List;

import com.leap92.bootcamp.orm.entities.Teacher;

public interface TeacherDAO {
	
	public void save (Teacher t);
	
	public List<Teacher> fetchAll();

}
