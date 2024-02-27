package com.demo.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.myshop.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	boolean existsByName(String name);

	Product findByName(String name);

}
