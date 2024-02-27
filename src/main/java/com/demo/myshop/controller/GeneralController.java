package com.demo.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.myshop.dto.Customer;
import com.demo.myshop.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GeneralController {

	@Autowired
	Customer customer;

	@Autowired
	CustomerService customerService;

	@GetMapping("/")
	public String loadHome() {
		return "Home";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("customer", customer);
		return "Signup";
	}

	@GetMapping("/signin")
	public String loadLogin() {
		return "Login";
	}

	@PostMapping("/signup")
	public String signup(@Valid Customer customer, BindingResult result) {
		if (result.hasErrors())
			return "Signup";
		else
			return customerService.save(customer, result);
	}

	@GetMapping("/send-otp/{id}")
	public String sendOtp(@PathVariable int id, ModelMap map) {
		return customerService.sendOtp(id, map);
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map, HttpSession session) {
		return customerService.verifyOtp(id, otp, map, session);
	}

	@GetMapping("/resend-otp/{id}")
	public String resendOtp(@PathVariable int id, ModelMap map) {
		return customerService.resendOtp(id, map);
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, ModelMap map, HttpSession session) {
		return customerService.login(email, password, map, session);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		session.setAttribute("successMessage", "Logout Success");
		return "redirect:/";
	}

	@GetMapping("/products")
	public String viewProducts(ModelMap map, HttpSession session) {
		return customerService.viewProducts(session, map);
	}

	@GetMapping("/add-cart/{id}")
	public String addToCart(@PathVariable int id, HttpSession session) {
		return customerService.addToCart(id, session);
	}

	@GetMapping("/cart")
	public String viewCart(HttpSession session, ModelMap map) {
		return customerService.viewCart(map, session);
	}

	@GetMapping("/remove-cart/{id}")
	public String removeFromCart(@PathVariable int id, HttpSession session) {
		return customerService.removeFromCart(id, session);
	}

	@GetMapping("/payment")
	public String paymentPage(HttpSession session,ModelMap map) {
		return customerService.paymentPage(session,map);
	}

	@PostMapping("/confirm-order/{id}")
	public String confirmOrder(HttpSession session,@PathVariable int id,@RequestParam String razorpay_payment_id) {
		return customerService.confirmOrder(session,id,razorpay_payment_id);
	}
	
	@GetMapping("/orders")
	public String viewOrders(HttpSession session,ModelMap map) {
		return customerService.viewOrders(session,map);
	}
}
