package com.demo.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.myshop.dto.Product;
import com.demo.myshop.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping
	public String loadDashboard(HttpSession session) {
		return adminService.loadDashboard(session);
	}

	@GetMapping("/add-product")
	public String loadAddProdcut(HttpSession session, ModelMap map) {
		return adminService.loadAddProduct(session, map);
	}

	@PostMapping("/add-product")
	public String addProdcut(@Valid Product product, BindingResult result, @RequestParam MultipartFile picture,
			HttpSession session, ModelMap map) {
		return adminService.addProduct(product, result, picture, session, map);
	}

	@PostMapping("/update-product")
	public String updateProdcut(@Valid Product product, BindingResult result, @RequestParam MultipartFile picture,
			HttpSession session, ModelMap map) {
		return adminService.updateProduct(product, result, picture, session, map);
	}

	@GetMapping("/manage-products")
	public String manageProducts(HttpSession session, ModelMap map) {
		return adminService.manageproducts(session, map);
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id, HttpSession session) {
		return adminService.deleteProduct(id, session);
	}

	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable int id, HttpSession session, ModelMap map) {
		return adminService.editProduct(id, session, map);
	}

	@GetMapping("/create-admin/{email}/{password}")
	public String createAdmin(@PathVariable String email, @PathVariable String password,HttpSession session) {
		return adminService.createAdmin(email,password,session);
	}

}
