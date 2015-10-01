package com.leap92.bootcamp.orm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.leap92.bootcamp.orm.dao.CourseDAO;
import com.leap92.bootcamp.orm.dao.CourseDAOImp;
import com.leap92.bootcamp.orm.dao.StudentDAO;
import com.leap92.bootcamp.orm.dao.StudentDAOImp;
import com.leap92.bootcamp.orm.dao.TeacherDAO;
import com.leap92.bootcamp.orm.dao.TeacherDAOImp;
import com.leap92.bootcamp.orm.entities.Course;
import com.leap92.bootcamp.orm.entities.Student;
import com.leap92.bootcamp.orm.entities.Teacher;

@Configuration
@ComponentScan("com.leap92.bootcamp.orm")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/high-school");
		dataSource.setUsername("root");
		
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory (DataSource dataSource) {
				
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		sessionBuilder.addAnnotatedClass(Student.class);
		sessionBuilder.addAnnotatedClass(Teacher.class);
		sessionBuilder.addAnnotatedClass(Course.class);
		sessionBuilder.addProperties(getHibernateProperties());
		
		return sessionBuilder.buildSessionFactory();
		
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
	
	@Autowired
	@Bean(name = "studentDAO")
	public StudentDAO getStudentDao (SessionFactory sessionFactory) {
		return new StudentDAOImp(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "teacherDAO")
	public TeacherDAO getTeacherDao (SessionFactory sessionFactory) {
		return new TeacherDAOImp(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "courseDAO")
	public CourseDAO getCourseDao (SessionFactory sessionFactory) {
		return new CourseDAOImp(sessionFactory);
	}

	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}

}
