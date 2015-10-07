package com.leap92.bootcamp.finalProject.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import com.leap92.bootcamp.finalProject.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername (String username);
	
	Collection<User> findAll();		

}
