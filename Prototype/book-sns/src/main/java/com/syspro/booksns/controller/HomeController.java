package com.syspro.booksns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syspro.booksns.model.BookInfo;
import com.syspro.booksns.service.BookInfoService;
import com.syspro.booksns.service.BookService;
import com.syspro.booksns.service.CommentService;
import com.syspro.booksns.service.PastBookInfoService;
import com.syspro.booksns.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	private final UserService userService;
	private final BookInfoService bookInfoService;
	private final BookService bookService;
	private final CommentService commentService;
	private final PastBookInfoService pastBookInfoService;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello";
	}
	
	@GetMapping("/test/{id}")
	@ResponseBody
	public BookInfo test(@PathVariable Long id) {
		return bookService.selectBookInfo(id);
	}
}
