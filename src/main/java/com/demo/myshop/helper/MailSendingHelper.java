package com.demo.myshop.helper;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.demo.myshop.dto.Customer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSendingHelper {

	@Autowired
	JavaMailSender mailSender;

	public void sendOtp(Customer customer) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("saishkulkarni7@gmail.com", "s13shMyshop");
			helper.setTo(customer.getEmail());
			helper.setSubject("Veification OTP");
			String gen = "";
			if (customer.getGender().equalsIgnoreCase("male"))
				gen = "Mr. ";
			else
				gen = "Ms. ";
			String body = "<html><body><h1>Hello " + gen + customer.getName() + ",</h1><h2>You OTP is : "
					+ customer.getOtp() + "</h2><h3>Thank and Regeards</h3></body></html>";
			helper.setText(body,true);
			mailSender.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
	}

	public void resendOtp(Customer customer) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("saishkulkarni7@gmail.com", "s13shMyshop");
			helper.setTo(customer.getEmail());
			helper.setSubject("Veification OTP");
			String gen = "";
			if (customer.getGender().equalsIgnoreCase("male"))
				gen = "Mr. ";
			else
				gen = "Ms. ";
			String body = "<html><body><h1>Hello " + gen + customer.getName() + ",</h1><h2>You OTP is : "
					+ customer.getOtp() + "</h2><h3>Thank and Regeards</h3></body></html>";
			helper.setText(body,true);
			mailSender.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
