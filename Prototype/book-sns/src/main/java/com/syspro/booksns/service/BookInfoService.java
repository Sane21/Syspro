package com.syspro.booksns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syspro.booksns.model.BookInfo;
import com.syspro.booksns.repository.BookInfoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BookInfoService {
	private final BookInfoMapper mapper;

	public List<BookInfo> selectAll(){
		return mapper.selectAll();
	}
	
	public BookInfo selectByPrimaryKey(Long userId) {
		return mapper.selectByPrimaryKey(userId);
	}
	
	public void save(BookInfo bookInfo) {
		if(bookInfo.getBookInfoId() == null) mapper.insert(bookInfo);
		else mapper.updateByPrimaryKey(bookInfo);
	}
	
	public int deleteByPrimaryKey(Long bookInfoId) {
		return mapper.deleteByPrimaryKey(bookInfoId);
	}
}
