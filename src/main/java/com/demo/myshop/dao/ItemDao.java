package com.demo.myshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.myshop.dto.Item;
import com.demo.myshop.repository.ItemRepository;

@Repository
public class ItemDao {

	@Autowired
	ItemRepository itemRepository;

	public Item findById(int id) {
		return itemRepository.findById(id).orElseThrow();
	}

	public void delete(Item item) {
		itemRepository.delete(item);
	}

	public void save(Item item) {
		itemRepository.save(item);
	}
}
