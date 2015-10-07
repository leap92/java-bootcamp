package com.leap92.bootcamp.finalProject.controllers;

import java.math.BigDecimal;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leap92.bootcamp.finalProject.model.ProductListItem;
import com.leap92.bootcamp.finalProject.model.ShoppingCart;
import com.leap92.bootcamp.finalProject.model.User;
import com.leap92.bootcamp.finalProject.repositories.ProductListItemRepository;
import com.leap92.bootcamp.finalProject.repositories.ShoppingCartRepository;
import com.leap92.bootcamp.finalProject.repositories.UserRepository;

@RestController
@RequestMapping(value="/cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private ProductListItemRepository productListItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ShoppingCart findById(@PathVariable long id) {
		return shoppingCartRepository.findOne(id);
	}
	
		
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> createShoppingCart(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userRepository.findByUsername(username);
		shoppingCart.setUser(user);
		this.shoppingCartRepository.save(shoppingCart);
			
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(shoppingCart.getId()).toUri());
			
		return new ResponseEntity<> (null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable Long id){
		shoppingCartRepository.delete(id);
	}
	
	@RequestMapping(value = "/{id}/totalprice", method = RequestMethod.GET)
	String getTotalPrice (@PathVariable Long id) {
		
		BigDecimal price = BigDecimal.valueOf(0);
		ShoppingCart sc = shoppingCartRepository.findOne(id);
				
		Collection<ProductListItem> items = productListItemRepository.findByShoppingCart(sc);
		for (ProductListItem p : items) {
			price = price.add(p.getSubTotal());
		}
		
		return "Your cart's total is: " + price.toString();
	}
	
	
	
	
}
