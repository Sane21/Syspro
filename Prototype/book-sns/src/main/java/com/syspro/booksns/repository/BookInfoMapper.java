package com.syspro.booksns.repository;

import java.util.List;

import com.syspro.booksns.model.BookInfo;

public interface BookInfoMapper {
	List<BookInfo> selectAll();
	
	BookInfo selectByPrimaryKey(Long id);
	
	int insert(BookInfo record);
	
	int updateByPrimaryKey(BookInfo record);
	
	int deleteByPrimaryKey(Long id);
}
