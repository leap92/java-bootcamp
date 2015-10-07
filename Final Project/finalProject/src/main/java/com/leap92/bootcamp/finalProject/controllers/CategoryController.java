package com.leap92.bootcamp.finalProject.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leap92.bootcamp.finalProject.model.Category;
import com.leap92.bootcamp.finalProject.repositories.CategoryRepository;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Gets all categories
	@RequestMapping(method = RequestMethod.GET)
	Collection<Category> readCategories () {
		return this.categoryRepository.findAll();
	}
	
	//Gets a category by Id
	@RequestMapping(value="{categoryId}", method = RequestMethod.GET)
	Category readCategory (@PathVariable Long categoryId) {
		validateCategory(categoryId);
		return this.categoryRepository.findOne(categoryId);
	}
	
	//Creates a category passed via JSON
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> createCategory(@RequestBody Category category, HttpServletRequest request, 	HttpServletResponse response) {
		this.categoryRepository.save(category);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(category.getId()).toUri());
		
		return new ResponseEntity<> (null, httpHeaders, HttpStatus.CREATED);
	}
	
	//Updates a category's data
	@RequestMapping(value="{categoryId}", method = RequestMethod.PUT)
	public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		validateCategory(categoryId);
		category.setId(categoryId);
		categoryRepository.save(category);		
	}
	
	//Deletes a category
	@RequestMapping(value="{categoryId}", method = RequestMethod.DELETE)
	public void deleteCategory (@PathVariable Long categoryId) {
		validateCategory(categoryId);
		categoryRepository.delete(categoryId);
	}
	
	//Validates if category exists and throws a RuntimeException
	private void validateCategory (Long categoryId) {
		Category c = categoryRepository.findOne(categoryId);
		if (c == null) {
			throw new RuntimeException ("No category with id: " + categoryId);
		}
	}

}
