package com.syspro.booksns.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private Long commentId;
	
	private Book targetBook;
	
	private User editUser;
	
	@NotBlank
	@Size(max = 1024)
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") //入力時の期待フォーマット
	@JsonFormat(pattern = "yyyy/MM/dd") //出力
	private LocalDate publishedDate;
}
