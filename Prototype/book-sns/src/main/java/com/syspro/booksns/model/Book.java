package com.syspro.booksns.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
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
	private String name;
	
	@NotBlank
	@Size(max =1024)
	private String author;
	
	@NotBlank
	private int ISBN;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime lastEditDate;
	
	@NotBlank
	private User editor; //外部キー
}
