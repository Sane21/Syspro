package com.syspro.booksns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syspro.booksns.model.Book;
import com.syspro.booksns.model.Comment;
import com.syspro.booksns.model.User;
import com.syspro.booksns.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
	private final CommentMapper mapper;

	public List<Comment> selectAll(){
		return mapper.selectAll();
	}
	
	public Comment selectByPrimaryKey(Long commentId) {
		return mapper.selectByPrimaryKey(commentId);
	}
	
	public void save(Comment comment) {
		if(comment.getCommentId() == null) mapper.insert(comment);
		else mapper.updateByPrimaryKey(comment);
	}
	
	public int deleteByPrimaryKey(Long commentId) {
		return mapper.deleteByPrimaryKey(commentId);
	}
	
	public Book selectBook(Comment record) {
		return mapper.selectBook(record);
	}
	
	public User selectUser(Long id) {
		return mapper.selectUser(id);
	}
}
