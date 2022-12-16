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
}