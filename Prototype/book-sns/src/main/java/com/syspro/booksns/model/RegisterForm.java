package com.syspro.booksns.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.syspro.booksns.validator.Characters;
import com.syspro.booksns.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
	@NotBlank
	@Size(max = 32, min = 5)
	@Characters
	@UniqueLogin
	private String userId;
	
	@NotBlank
	@Size(max = 20, min = 6)
	@Characters
	private String password;
	
	
	@NotBlank
	@Size(max = 20, min = 6)
	@Pattern(regexp="[0-9a-zA-Z!_,.@~`!#^\\-*()+={}\\[\\]|:;]+")
	private String passwordConfirm;

	@Size(max = 60) //文字列の最大長は60文字
	private String name;
	
	@AssertTrue(message = "パスワードが一致しません")
	public boolean isPasswordValid() {
		if(password == null || password.isEmpty()) {
			return true;
		}
		return password.equals(passwordConfirm);
	}
}
