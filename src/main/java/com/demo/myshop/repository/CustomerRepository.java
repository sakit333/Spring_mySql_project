package com.demo.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.myshop.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

	Customer findByEmail(String email);

}
