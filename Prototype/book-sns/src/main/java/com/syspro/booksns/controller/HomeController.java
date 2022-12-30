package com.syspro.booksns.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String index(Model model) {
		model.addAttribute("books", bookService.selectAll());
		return "index";
	}
	
	@GetMapping("/result")
	public String searchResult(@RequestParam(defaultValue = " ") String word, Model model) {
		model.addAttribute("books", bookService.selectByTitle(word));
		return "index";
	}
	
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
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		Book book = bookService.selectByPrimaryKey(id);
		model.addAttribute("book", book);
		return "bookForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id, Authentication loginUser) {
		Book book = bookService.selectByPrimaryKey(id);
		//ユーザIDと投稿者が同じじゃなきゃ削除できない
		if(loginUser.getName().equals(book.getEditor().getUserId())){
			bookService.deleteByPrimaryKey(id);			
		}
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
	
	@GetMapping("/search")
	public String search() {
		return "searchForm";
	}
}
