package com.example.spring.google.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Profile;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail")
public class GmailController
		extends GmailBaseController {

	@GetMapping("/profile")
	public Mono<Profile> profile(
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		Map<String, Object> pathValue = new HashMap<>();
		pathValue.put("userId", "me");

		return getMono(client, "users/{userId}/profile", pathValue, Profile.class);
	}

}