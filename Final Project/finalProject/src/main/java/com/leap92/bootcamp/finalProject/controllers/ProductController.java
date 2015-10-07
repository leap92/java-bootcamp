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
import com.leap92.bootcamp.finalProject.model.Product;
import com.leap92.bootcamp.finalProject.repositories.CategoryRepository;
import com.leap92.bootcamp.finalProject.repositories.ProductRepository;

@RestController
@RequestMapping(value="/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	Collection<Product> readProducts () {
		return this.productRepository.findAll();
	}
	
	@RequestMapping(value="/byId/{productId}", method = RequestMethod.GET)
	Product readProductById (@PathVariable Long productId) {
		return this.productRepository.findOne(productId);
	}	
	
	@RequestMapping(value="/byName/{productName}", method = RequestMethod.GET)
	Product readProductByName (@PathVariable String productName) {
		return this.productRepository.findByName(productName);
	}
	
	@RequestMapping(value="/byCategoryName/{categoryName}", method = RequestMethod.GET)
	Collection<Product> readProductsByCategoryName(@PathVariable String categoryName) {
		Category c = categoryRepository.findByName(categoryName);
		return this.productRepository.findByCategory(c);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> createProduct(@RequestBody Product product, HttpServletRequest request, 	HttpServletResponse response) {
		this.productRepository.save(product);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri());
		
		return new ResponseEntity<> (null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{productId}", method = RequestMethod.PUT)
	void updateProduct(@PathVariable Long productId, @RequestBody Product product) {
		validateProduct(productId);
		product.setId(productId);
		productRepository.save(product);
	}
	
	
	@RequestMapping(value="{productId}", method = RequestMethod.DELETE)
	void deleteProduct (@PathVariable Long productId) {
		productRepository.delete(productId);
	}
	
	private void validateProduct (Long productId) {
		Product p = productRepository.findOne(productId);
		if (p == null) {
			throw new RuntimeException("No product with id: " + productId);
		}
	}
	
}
