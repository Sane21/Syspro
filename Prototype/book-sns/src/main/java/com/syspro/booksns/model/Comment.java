package com.syspro.booksns.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private Long commentId;
	
	private Long targetBookId;
	
	private Long edituserId;
	
	@NotBlank
	@Size(max = 1024)
	private String content;
	
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd") //入力時の期待フォーマット
	@JsonFormat(pattern = "yyyy/MM/dd") //出力
	private LocalDate publishedDate;
}
