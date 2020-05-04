package com.example.spring.google.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class IndexController {

	protected final WebClient webClient;

	@Autowired
	public IndexController(WebClient webClient) {
		this.webClient = webClient;
	}

	@GetMapping("/")
	public Mono<String> index(@AuthenticationPrincipal Mono<OAuth2User> oauth2User) {
		return oauth2User
				.map(user -> user.getAttribute("name"))
				.map(name -> String.format("Hi, %s", name));
	}
}
