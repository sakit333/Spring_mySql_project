package com.demo.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.myshop.dto.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {

}
