package com.leap92.bootcamp.orm.dao;

import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.leap92.bootcamp.orm.entities.Course;

public class CourseDAOImp implements CourseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CourseDAOImp() {}
	
	public CourseDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(Course c) {
		sessionFactory.getCurrentSession().persist(c);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Course> fetchAll() {
		List<Course> courses = (List<Course>) sessionFactory.getCurrentSession()
				.createCriteria(Course.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return courses;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Course> getStudentCoursesById(int id) {
		String hql = "SELECT c FROM Course c "
				+ "JOIN c.students s WHERE s.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Course> courses = query.list();
		
		return courses;
	}

}
