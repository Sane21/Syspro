package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.PastBookInfo;

@Mapper
public interface PastBookInfoMapper {
	List<PastBookInfo> selectAll();
	
	PastBookInfo selectByPrimaryKey(Long id);
	
	int insert(PastBookInfo record);
	
	int updateByPrimaryKey(PastBookInfo record);
	
	int deleteByPrimaryKey(Long id);
}
