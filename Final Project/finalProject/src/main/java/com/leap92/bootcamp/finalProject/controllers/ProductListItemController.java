package com.leap92.bootcamp.finalProject.controllers;

import java.math.BigDecimal;
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

import com.leap92.bootcamp.finalProject.model.Product;
import com.leap92.bootcamp.finalProject.model.ProductListItem;
import com.leap92.bootcamp.finalProject.model.ShoppingCart;
import com.leap92.bootcamp.finalProject.repositories.ProductListItemRepository;
import com.leap92.bootcamp.finalProject.repositories.ProductRepository;
import com.leap92.bootcamp.finalProject.repositories.ShoppingCartRepository;

@RestController
@RequestMapping(value="/cart/")
public class ProductListItemController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductListItemRepository productListItemRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@RequestMapping(value="/{cartId}/productlist")
	Collection<ProductListItem> readProductListItems(@PathVariable Long cartId) {
		ShoppingCart sc = shoppingCartRepository.findOne(cartId);
		
		return productListItemRepository.findByShoppingCart(sc);
	}
	
	@RequestMapping(value="/{cartId}/productlist", method = RequestMethod.POST)
	ResponseEntity<?> createProductListItem(@RequestBody ProductListItem productListItem, @PathVariable Long cartId, HttpServletRequest request, HttpServletResponse response) {
		//Check if shopping cart exists
		ShoppingCart sc = shoppingCartRepository.findOne(cartId);
		if (sc == null) {
			throw new RuntimeException("Can't find shopping cart with id: " + cartId);
		}		
		productListItem.setShoppingCart(sc);
		
		//Get product price from the ProductListItem to calculate subTotal
		Product p = productRepository.findOne(productListItem.getProduct().getId());
		BigDecimal subTotal = p.getPrice().multiply(BigDecimal.valueOf(productListItem.getQuantity()));
		productListItem.setSubTotal(subTotal);
		this.productListItemRepository.save(productListItem);
			
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productListItem.getId()).toUri());
			
		return new ResponseEntity<> (null, httpHeaders, HttpStatus.CREATED);
	}
	
	//All that can be edited from a ProductListItem is its quantity
	@RequestMapping(value="/{cartId}/productlist/{id}", method = RequestMethod.PUT)
	void updateProductListItem (@RequestBody ProductListItem productListItem, @PathVariable Long id) {
		
		ProductListItem p = productListItemRepository.findOne(id);
		//Check if id in PathVariable corresponds with an existing ProductListItem
		if (p == null) {
			throw new RuntimeException("Can't find product list item with id:" + id);
		}
		//Set the new quantity and new subtotal price
		p.setQuantity(productListItem.getQuantity());
		p.setSubTotal(p.getProduct().getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		
		//Update with new values
		productListItemRepository.save(p);
	}
	
	@RequestMapping(value="/{cartId}/productlist/{id}", method = RequestMethod.DELETE)
	void deleteProductListItem (@PathVariable Long id) {
		ProductListItem p = productListItemRepository.findOne(id);
		if (p == null) {
			throw new RuntimeException("Can't find product list item with id:" + id);
		}
		productListItemRepository.delete(id);
	}

}
