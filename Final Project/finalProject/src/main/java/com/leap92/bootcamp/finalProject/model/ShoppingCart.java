package com.leap92.bootcamp.finalProject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<ProductListItem> productlistitem;
	
	@OneToOne
	private User user;
	
	public ShoppingCart () {}
	
	public ShoppingCart (User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProductListItem> getProducts() {
		return productlistitem;
	}

	public void setProducts(List<ProductListItem> products) {
		this.productlistitem = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
