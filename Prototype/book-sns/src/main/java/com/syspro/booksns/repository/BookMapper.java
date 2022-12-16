package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.Book;

@Mapper
public interface BookMapper {
	List<Book> selectAll();
	
	Book selectByPrimaryKey(Long id);
	
	int insert(Book record);
	
	int updateByPrimaryKey(Book record);
	
	int deleteByPrimaryKey(Long id);
}
