package com.leap92.bootcamp.finalProject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.leap92.bootcamp.finalProject.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
