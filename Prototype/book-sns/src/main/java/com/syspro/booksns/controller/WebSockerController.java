package com.syspro.booksns.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSockerController {
	
	@MessageMapping("/update")
	@SendTo("/topic/users")
	public String update() throws Exception {
		return "update page";
	}
}
