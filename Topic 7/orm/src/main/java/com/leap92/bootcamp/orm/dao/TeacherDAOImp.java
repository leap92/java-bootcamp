package com.leap92.bootcamp.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.leap92.bootcamp.orm.entities.Teacher;

public class TeacherDAOImp implements TeacherDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TeacherDAOImp() {}
	
	public TeacherDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(Teacher t) {
		sessionFactory.getCurrentSession().persist(t);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Teacher> fetchAll() {
		List<Teacher> teachers = (List<Teacher>) sessionFactory.getCurrentSession()
				.createCriteria(Teacher.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return teachers;
	}
	
	

}
