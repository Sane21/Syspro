package com.syspro.booksns.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private Long bookId;
	
	@NotBlank
	private Long lastEditedIndoId;
}
