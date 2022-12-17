package com.syspro.booksns.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookInfo {
	private Long bookInfoId;
	
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
	private Long editorId; //外部キー
	
	private List<PastBookInfo> pastBookInfos; //外部キー
}
