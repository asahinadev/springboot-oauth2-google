package com.example.spring.google.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.spring.google.dto.GmailMessages;
import com.example.spring.google.dto.GmailProfile;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/gmail")
public class GmailController {

	protected static final String API = "https://www.googleapis.com/gmail/v1/";

	@Autowired
	WebClient webClient;

	@GetMapping("/profile")
	public Flux<GmailProfile> profile(
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		Map<String, Object> pathValue = new HashMap<>();
		pathValue.put("userId", "me");

		return webClient.get()
				.uri(API + "users/{userId}/profile", pathValue)
				.attributes(oauth2AuthorizedClient(client))
				.retrieve()
				.bodyToFlux(GmailProfile.class);
	}

	@GetMapping("/messages")
	public Flux<GmailMessages> messages(
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		Map<String, Object> pathValue = new HashMap<>();
		pathValue.put("userId", "me");

		return webClient.get()
				.uri(API + "users/{userId}/messages", pathValue)
				.attributes(oauth2AuthorizedClient(client))
				.retrieve()
				.bodyToFlux(GmailMessages.class);
	}

	private Consumer<Map<String, Object>> oauth2AuthorizedClient(OAuth2AuthorizedClient client) {
		return ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client);
	}
}
