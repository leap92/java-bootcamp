package com.leap92.bootcamp.orm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leap92.bootcamp.orm.entities.Student;

@Repository
public class StudentDAOImp implements StudentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentDAOImp() {}
	
	public StudentDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(Student s) {
		sessionFactory.getCurrentSession().persist(s);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Student> fetchAll() {
		List<Student> students = (List<Student>) sessionFactory.getCurrentSession()
				.createCriteria(Student.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return students;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Student> getCourseStudentsById(int id) {
		String hql = "SELECT s FROM Student s "
				+ "JOIN s.courses c WHERE c.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Student> students = query.list();
		
		return students;
	}

}
