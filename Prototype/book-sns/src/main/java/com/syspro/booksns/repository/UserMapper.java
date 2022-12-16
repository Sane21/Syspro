package com.syspro.booksns.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.syspro.booksns.model.User;

@Mapper
public interface UserMapper {
	public List<User> selectAll();
}
