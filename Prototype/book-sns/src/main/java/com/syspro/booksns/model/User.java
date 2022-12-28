package com.syspro.booksns.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.syspro.booksns.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter //ゲッター、セッターが自動生成されるようにする
@Setter
public class User {
	@NotBlank
	@Size(max = 32, min = 5)
	@Pattern(regexp="[0-9a-zA-Z!_,.@~`!#^\\-*()+={}\\[\\]|:;]+")
	@UniqueLogin
	private String userId;
	
	@NotBlank
	@Size(max = 2048)
	@Pattern(regexp="[0-9a-zA-Z!_,.@~`!#^\\-*()+={}\\[\\]|:;]+")
	private String password;
	
	@Size(max = 60) //文字列の最大長は60文字
	private String name;
	
	@Size(max =512)
	private String profile;
}
