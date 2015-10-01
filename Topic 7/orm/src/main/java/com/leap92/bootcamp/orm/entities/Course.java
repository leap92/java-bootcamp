package com.leap92.bootcamp.orm.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@Column(name="idcourse")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="assignedteacher")
	private int assignedteacherid;
	
	@Column(name="hoursperweek")
	private int hoursperweek;
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAssignedteacherid() {
		return assignedteacherid;
	}

	public void setAssignedteacherid(int assignedteacherid) {
		this.assignedteacherid = assignedteacherid;
	}

	public int getHoursperweek() {
		return hoursperweek;
	}

	public void setHoursperweek(int hoursperweek) {
		this.hoursperweek = hoursperweek;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", assignedteacherid=" + assignedteacherid + ", hoursperweek=" + hoursperweek + "]";
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
