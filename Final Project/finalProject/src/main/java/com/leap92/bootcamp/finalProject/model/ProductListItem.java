package com.leap92.bootcamp.finalProject.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ProductListItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@NotNull
	private Product product;
	
	@NotNull
	private Long quantity;
	
	private BigDecimal subTotal;
	
	@ManyToOne
	@NotNull
	@JsonBackReference
	private ShoppingCart shoppingCart;
	
	public ProductListItem() {}
	
	public ProductListItem(Product product, Long quantity, BigDecimal subTotal, ShoppingCart shoppingCart) {
		this.product = product;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.shoppingCart = shoppingCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
