package com.demo.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.myshop.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>
{

}
