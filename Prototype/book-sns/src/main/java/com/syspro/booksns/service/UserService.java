package com.syspro.booksns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syspro.booksns.model.User;
import com.syspro.booksns.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
	private final UserMapper mapper;

	public List<User> selectAll(){
		return mapper.selectAll();
	}
	
	public User selectByPrimaryKey(String userId) {
		return mapper.selectByPrimaryKey(userId);
	}
	
	public boolean insert(User user) {
		//既に同じIDのユーザがいる場合は使用不可に
		if(mapper.selectByPrimaryKey(user.getUserId()) != null) {
			return false;
		}
		mapper.insert(user);
		return true;
	}
	
	public int updateByPrimaryKey(User user) {
		if(user.getUserId() != null) {
			return -1;
		}
		return mapper.updateByPrimaryKey(user);
	}
	
	public int deleteByPrimaryKey(String userId) {
		return mapper.deleteByPrimaryKey(userId);
	}
}
