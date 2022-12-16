package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.User;

@Mapper
public interface UserMapper {
	List<User> selectAll();
	
	User selectByPrimaryKey(Long id);
	
	int insert(User record);
	
	int updateByPrimaryKey(User record);
	
	int deleteByPrimaryKey(Long id);
}
