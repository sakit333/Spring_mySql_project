package com.demo.myshop.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 3, max = 10, message = "* Should be between 3 to 10 charecters")
	private String name;
	@NotEmpty(message = "* It is Compulsory Field")
	@Email(message = "* Enter Proper Email Format")
	private String email;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "* Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	private String password;
	@NotEmpty(message = "* It is Compulsory Field")
	private String gender;
	@DecimalMax(value = "9999999999", inclusive = true, message = "* Enter Proper Mobile Number")
	@DecimalMin(value = "6000000000", inclusive = true, message = "* Enter Proper Mobile Number")
	private long mobile;
	@NotNull(message = "* It is Compulsory Field")
	@Past(message = "* Enter proper Dob")
	private LocalDate dob;
	private String role;
	private int otp;
	private boolean verified;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	Cart cart = new Cart();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ShoppingOrder> orders = new ArrayList<ShoppingOrder>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<ShoppingOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ShoppingOrder> orders) {
		this.orders = orders;
	}
	
	
	
}
