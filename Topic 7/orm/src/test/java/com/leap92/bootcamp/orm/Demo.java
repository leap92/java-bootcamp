package com.leap92.bootcamp.orm;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leap92.bootcamp.orm.config.ApplicationContextConfig;
import com.leap92.bootcamp.orm.dao.CourseDAO;
import com.leap92.bootcamp.orm.dao.StudentDAO;
import com.leap92.bootcamp.orm.dao.TeacherDAO;
import com.leap92.bootcamp.orm.entities.Course;
import com.leap92.bootcamp.orm.entities.Student;
import com.leap92.bootcamp.orm.entities.Teacher;

public class Demo {
	
	public static void main(String[] args) {
				
		/*
		 * Have in mind that if you try to execute this class twice
		 * without deleting the previously added rows
		 * it will throw an exception since the PK will be duplicated
		 */
		
		Student s = new Student();
		s.setFirstName("Juan");
		s.setLastName("Topo");
		s.setId(16);
		s.setBirthday(java.sql.Date.valueOf("1992-02-20"));
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		//Store a student (exercise 3)
		studentDAO.save(s);
		
		Teacher t = new Teacher();
		t.setFirstName("Jose");
		t.setLastName("Seul");
		t.setId(4184);
		t.setBirthday(java.sql.Date.valueOf("1974-09-01"));
		
		TeacherDAO teacherDAO = (TeacherDAO) context.getBean("teacherDAO");
		//Store a teacher (exercise 3)
		teacherDAO.save(t);
		
		Course c = new Course();
		c.setId(6);
		c.setName("Seminary");
		c.setAssignedteacherid(4184);
		c.setHoursperweek(20);
		
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");
		//Store a course (exercise 3)
		courseDAO.save(c);
		
		/*
		 * These 3 methods fetch all students, teachers and courses
		 * as required on exercise 3
		 */
		List<Student> students = studentDAO.fetchAll();
		for (Student student : students) {
			System.out.println(student);
		}
		
		List<Teacher> teachers = teacherDAO.fetchAll();
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
		
		List<Course> courses = courseDAO.fetchAll();
		for (Course course : courses) {
			System.out.println(course);
		}
		
		//Get all courses attended by a given student (exercise 4)
		List<Course> studentCourses = courseDAO.getStudentCoursesById(2);
		for (Course course : studentCourses) {
			System.out.println(course);
		}
		
		//Get all students that attend a certain course (exercise 5)
		List<Student> courseStudents = studentDAO.getCourseStudentsById(1);
		for (Student student : courseStudents) {
			System.out.println(student);
		}
		/*Close the context, eclipse says it's a resource leak
		 * if I don't
		 */
		((ConfigurableApplicationContext)context).close();
	}

}
