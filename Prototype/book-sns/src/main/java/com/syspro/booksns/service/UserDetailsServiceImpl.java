package com.syspro.booksns.service;

import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syspro.booksns.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserService userService;
	
	/**
	 * 見つかったユーザを返すメソッド
	 * return ユーザ
	 */
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		//idをもとにUserオブジェクトを取得
		var user = userService.selectByPrimaryKey(id);
		//取得したユーザ情報をもとにUserオブジェクトを生成
		if(user == null) {
			//ユーザが見つからないときは例外をスロー
			throw new UsernameNotFoundException(id + "not found");
		}
		return createUserDetails(user);
	}
	
	//フレームワークのクラスと名前が被っているがUserは別物
	public org.springframework.security.core.userdetails.User createUserDetails(User user) {
		var grantedAuthorities = new HashSet<GrantedAuthority>();
		return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthorities);
	}
}
