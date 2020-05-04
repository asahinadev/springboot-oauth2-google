package com.example.spring.google.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Histories;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail")
public class GmailHistoryController
		extends GmailBaseController {

	@GetMapping("/history")
	public Mono<Histories> history(
			@RequestParam(name = "startHistoryId", defaultValue = "") String startHistoryId,
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("startHistoryId", startHistoryId);

		Map<String, Object> pathValue = new HashMap<>();
		pathValue.put("userId", "me");

		return getMono(client, "users/{userId}/history", pathValue, params, Histories.class);
	}

}
