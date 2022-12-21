package com.syspro.booksns.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter //ゲッター、セッターが自動生成されるようにする
@Setter
public class User {
	private Long userId;
	
	@NotBlank //文字列がnullか空文字、空白でないかを検証
	@Size(max = 60) //文字列の最大長は60文字
	private String name;
	
	@Size(max =1024)
	private String profile;
}
