package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.Book;
import com.syspro.booksns.model.Comment;
import com.syspro.booksns.model.User;

@Mapper
public interface CommentMapper {
	List<Comment> selectAll();
	
	Comment selectByPrimaryKey(Long id);
	
	int insert(Comment record);
	
	int updateByPrimaryKey(Comment record);
	
	int deleteByPrimaryKey(Long id);
	
	Book selectBook(Comment record);
	
	User selectUser(Long id);
	
	List<Comment> selectBookComments(Long bookId);
}
