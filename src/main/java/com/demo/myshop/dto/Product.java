package com.demo.myshop.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Component
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 5, max = 25, message = "* Should be between 5 to 25 charecters")
	private String name;
	@Size(min = 5, max = 25, message = "* Should be between 5 to 25 charecters")
	private String category;
	@DecimalMin(value = "1", message = "* Enter atleast Minimum price: 1")
	private double price;
	@Size(min = 10, max = 100, message = "* Should be between 10 to 100 charecters")
	private String description;
	@DecimalMin(value = "1", message = "* Enter atleast Minimum 1")
	private int stock;
//	@Lob
//	@Column(columnDefinition = "MEDIUMBLOB")
//	private byte[] image;
	private String imagePath;
}
