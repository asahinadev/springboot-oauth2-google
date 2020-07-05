package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.WatchRequest;
import com.example.spring.google.dto.gmail.WatchResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/watch")
public class WatchController extends BaseController<WatchResponse> {

	public WatchController() {
		super(WatchResponse.class);
	}

	private static final String PATH = "users/{userId}/watch";

	@PostMapping
	public Mono<WatchResponse> post(
			@RequestBody WatchRequest body,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client

	) {
		return post(client, PATH, varsMe, body);
	}

}
