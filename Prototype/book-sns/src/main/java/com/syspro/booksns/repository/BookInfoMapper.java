package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.BookInfo;
import com.syspro.booksns.model.PastBookInfo;
import com.syspro.booksns.model.User;

@Mapper
public interface BookInfoMapper {
	List<BookInfo> selectAll();
	
	BookInfo selectByPrimaryKey(Long id);
	
	int insert(BookInfo record);
	
	int updateByPrimaryKey(BookInfo record);
	
	int deleteByPrimaryKey(Long id);
	
	User selectUser(BookInfo bookInfo);
	
	List<PastBookInfo> selectInfos(BookInfo bookInfo);
}
