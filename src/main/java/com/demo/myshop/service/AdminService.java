package com.demo.myshop.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.demo.myshop.dto.Product;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

public interface AdminService {

	String loadDashboard(HttpSession session);

	String loadAddProduct(HttpSession session, ModelMap map);

	String addProduct(Product product, BindingResult result, MultipartFile picture, HttpSession session, ModelMap map);

	String manageproducts(HttpSession session, ModelMap map);

    String deleteProduct(int id, HttpSession session);

    String editProduct(int id, HttpSession session, ModelMap map);

    String updateProduct(@Valid Product product, BindingResult result, MultipartFile picture, HttpSession session,
            ModelMap map);

    String createAdmin(String email, String password, HttpSession session);

}
