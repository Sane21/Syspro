package com.syspro.booksns.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private Long bookId;
	
	@NotBlank
	@Size(max = 1024)
	private String title;
	
	@NotBlank
	@Size(max =64)
	private String author;
	
	@NotBlank
	@Pattern(regexp="\\d{13}")
	private String ISBN;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime lastEditDate;

	private User editor; //外部キー
	
	private String detail;
	
	private String url;
}
