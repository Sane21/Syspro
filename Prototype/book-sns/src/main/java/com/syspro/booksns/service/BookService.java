package com.syspro.booksns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syspro.booksns.model.Book;
import com.syspro.booksns.model.User;
import com.syspro.booksns.repository.BookMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {
	private final BookMapper mapper;

	public List<Book> selectAll(){
		return mapper.selectAll();
	}
	
	public Book selectByPrimaryKey(Long bookId) {
		return mapper.selectByPrimaryKey(bookId);
	}
	
	public void save(Book book) {
		if(book.getBookId() == null) mapper.insert(book);
		else mapper.updateByPrimaryKey(book);
	}
	
	public int deleteByPrimaryKey(Long bookId) {
		return mapper.deleteByPrimaryKey(bookId);
	}
	
	public User selectUser(Long id) {
		return mapper.selectUser(id);
	}
}
