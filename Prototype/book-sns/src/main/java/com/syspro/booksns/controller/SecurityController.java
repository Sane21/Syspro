package com.syspro.booksns.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.syspro.booksns.model.RegisterForm;
import com.syspro.booksns.model.User;
import com.syspro.booksns.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login() {
		return "sample_login";
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute RegisterForm registerForm) {
		return "sample_signup";
	}
	
	@PostMapping("/signup")
	public String process(@Validated @ModelAttribute RegisterForm registerForm, 
			BindingResult result) {
		if(result.hasErrors()) return "sample_signup";
		User user = new User();
		user.setUserId(registerForm.getUserId());
		if(registerForm.getName() == "") user.setName("No name");
		else user.setName(registerForm.getName());
		String encodePass = passwordEncoder.encode(registerForm.getPassword());
		user.setPassword(encodePass);
		userService.insert(user);
		
		return "redirect:/";
	}
}
