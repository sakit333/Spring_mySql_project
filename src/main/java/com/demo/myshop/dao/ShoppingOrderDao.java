package com.demo.myshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.myshop.dto.ShoppingOrder;
import com.demo.myshop.repository.ShoppingOrderRepository;

@Repository
public class ShoppingOrderDao {

	@Autowired
	ShoppingOrderRepository orderRepository;

	public void saveOrder(ShoppingOrder order) {
		orderRepository.save(order);
	}

	public ShoppingOrder findOrderById(int id) {
		return orderRepository.findById(id).orElseThrow();
	}
}
