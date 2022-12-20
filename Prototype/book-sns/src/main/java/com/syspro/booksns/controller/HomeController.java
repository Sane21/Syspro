package com.syspro.booksns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syspro.booksns.service.BookService;
import com.syspro.booksns.service.CommentService;
import com.syspro.booksns.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	private final UserService userService;
	private final BookService bookService;
	private final CommentService commentService;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("users", userService.selectAll());
		return "index";
	}
}
