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
	
	public void save(User user) {
		if(user.getUserId() == null) mapper.insert(user);
		else mapper.updateByPrimaryKey(user);
	}
	
	public int deleteByPrimaryKey(String userId) {
		return mapper.deleteByPrimaryKey(userId);
	}
}
