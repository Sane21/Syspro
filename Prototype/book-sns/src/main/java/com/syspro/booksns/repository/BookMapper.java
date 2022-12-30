package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.Book;
import com.syspro.booksns.model.User;

@Mapper
public interface BookMapper {
	List<Book> selectAll();
	
	Book selectByPrimaryKey(Long id);
	
	int insert(Book record);
	
	int updateByPrimaryKey(Book record);
	
	int deleteByPrimaryKey(Long id);
	
	User selectUser(String id);
	
	List<Book> selectByTitle(String title);
}
