package com.syspro.booksns.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		//API連携部分
		JsonNode node = getResult(book.getISBN());
		System.out.println(node.path(0).asText(null));
		if(node.path(0).asText(null) != null) {
			if(getTitle(node) != null) book.setTitle(getTitle(node));				
			if(getDetail(node) != null) book.setDetail(getDetail(node));				
			if(getURL(node) != null) book.setUrl(getURL(node));							
			if(getAuthor(node) != null) book.setAuthor(getAuthor(node));
		}
		
		
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
	
	private JsonNode getResult(String isbn) {
		String result = "";
		JsonNode root = null;
		try {
			URL url = new URL("https://api.openbd.jp/v1/get?isbn=" + isbn);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = in.readLine()) != null) result += tmp;
			
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(result);
			in.close();
			con.disconnect();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	private String getDetail(JsonNode node) {
		String detail = null;
		JsonNode contents = node.path(0).path("onix").path("CollateralDetail").path("TextContent");
		detail = contents.path(0).path("Text").asText(null);
		if(detail == null) return detail;
		if(contents.path(0).path("Text").asText(null).length() <= 70) {
			//本の説明が短すぎるときは次のやつの説明も追加
			detail += contents.path(1).path("Text").asText(null);
		}
		detail = detail.replace("\\n", "\n");
		detail = detail.replace("\"", "");			
	
		return detail;
		
	}
	
	private String getURL(JsonNode node) {
		String url = null;
		JsonNode urlNode = node.path(0).path("onix").path("CollateralDetail").path("SupportingResource").path(0).path("ResourceVersion")
				.path(0).path("ResourceLink");
		url = urlNode.asText(null);
		
		
		if(url != null) url = urlNode.asText(null).replace("\"", "");
		return url;
		
	}
	
	private String getTitle(JsonNode node) {
		String title = null;
		JsonNode titleNode = node.path(0).path("onix").path("DescriptiveDetail")
				.path("TitleDetail").path("TitleElement").path("TitleText").path("content");
		title = titleNode.asText(null);
		if(title != null) title = title.replace("\"", "");
		return title;
	}
	
	private String getAuthor(JsonNode node) {
		String author = "";
		JsonNode authorNode = node.path(0).path("summary").path("author");
		author = authorNode.asText(null);
		if(author != null) {
			author = author.replace("／著", " ");
		}
		return author;
	}
}
