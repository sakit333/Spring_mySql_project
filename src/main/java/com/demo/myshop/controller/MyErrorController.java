package com.demo.myshop.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handler(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		int code = (Integer) status;
		if (code == 404)
			return "404";
		else
			return "error";

	}

}
