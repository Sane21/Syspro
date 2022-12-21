package com.syspro.booksns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syspro.booksns.model.Book;
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
	
//	@GetMapping("/test")
//	public String test(Model model) {
//		model.addAttribute("users", userService.selectAll());
//		return "index";
//	}
	
	@GetMapping("/selectUser/{id}")
	public String test2(Model model, @PathVariable Long id) {
		model.addAttribute("user", bookService.selectUser(Long.valueOf(id)));
		return "index";
	}
	
	@GetMapping("/add")
	public String addBook(@ModelAttribute Book book) {
		return "bookForm";
	}
	
	@PostMapping("/add")
	public String postBook(@Validated @ModelAttribute Book book, BindingResult result) {
		if(result.hasErrors()) { //バリデーションチェックの部分
			return "bookForm";
		}
		
		book.setEditor(userService.selectByPrimaryKey(Long.valueOf(3)));
		bookService.save(book);
		return "redirect:/";
	}
}
