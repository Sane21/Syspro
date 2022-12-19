package com.syspro.booksns.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private Long bookId;
	
	private BookInfo lastEditedBookInfo;
}
