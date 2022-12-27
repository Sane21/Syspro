package com.syspro.booksns.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syspro.booksns.model.Book;
import com.syspro.booksns.model.Comment;
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
	public List<Book> index() {
		return bookService.selectAll();
	}
	
//	@GetMapping("/test")
//	public String test(Model model) {
//		model.addAttribute("users", userService.selectAll());
//		return "index";
//	}
	
	@GetMapping("/add")
	public String addBook(@ModelAttribute Book book) {
		return "bookForm";
	}
	
	@PostMapping("/add")
	public String postBook(@Validated @ModelAttribute Book book, BindingResult result,
			Authentication loginUser) {
		if(result.hasErrors()) { //バリデーションチェックの部分
			return "bookForm";
		}
		String userId = loginUser.getName();
		book.setEditor(userService.selectByPrimaryKey(userId));
		bookService.save(book);
		return "redirect:/";
	}
	
	@GetMapping("/comment")
	public String addComment(@ModelAttribute Comment comment) {
		return "commentForm";
	}
	
	@PostMapping("/comment")
	public String postComment(@Validated @ModelAttribute Comment comment, BindingResult result,
			Authentication loginUser) {
		if(result.hasErrors()) return "commentForm";
		String editorId = loginUser.getName();
		comment.setEditUser(userService.selectByPrimaryKey(editorId));
		commentService.save(comment);
		return "redirect:/";
	}
}
