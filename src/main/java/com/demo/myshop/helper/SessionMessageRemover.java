package com.demo.myshop.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionMessageRemover {

	public void removeMessage() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();
		session.removeAttribute("successMessage");
		session.removeAttribute("failMessage");
	}

}
