package com.leap92.bootcamp.finalProject.repositories;


import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.leap92.bootcamp.finalProject.model.ProductListItem;
import com.leap92.bootcamp.finalProject.model.ShoppingCart;

public interface ProductListItemRepository extends CrudRepository<ProductListItem, Long> {


	Collection<ProductListItem> findByShoppingCart(ShoppingCart sc);
	
}
