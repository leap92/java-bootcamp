package com.leap92.bootcamp.finalProject.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.leap92.bootcamp.finalProject.model.Category;

public interface CategoryRepository extends CrudRepository <Category, Long>{
	
	Collection<Category> findAll();

	Category findByName(String name);

}
