package com.leap92.bootcamp.finalProject.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.leap92.bootcamp.finalProject.model.Category;
import com.leap92.bootcamp.finalProject.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	Collection<Product> findAll ();
	
	Product findByName(String name);

	Collection<Product> findByCategory(Category c);

}
