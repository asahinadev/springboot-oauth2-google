package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Histories;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/history")
public class HistoryController extends BaseController<Histories> {

	private static final String PATH = "users/{userId}/history";

	public HistoryController() {
		super(Histories.class);
	}

	@GetMapping
	public Mono<Histories> history(
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		return get(client, PATH, varsMe);
	}

	@GetMapping(params = "startHistoryId")
	public Mono<Histories> history(
			@RequestParam LinkedMultiValueMap<String, String> params, @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

		return get(client, PATH, varsMe, params);
	}

}
