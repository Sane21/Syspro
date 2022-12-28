package com.syspro.booksns.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharactersValidator implements ConstraintValidator<Characters, String>{
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("[0-9a-zA-Z!_,.@~`!#^\\-*()+={}\\[\\]|:;]+");
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}
}
