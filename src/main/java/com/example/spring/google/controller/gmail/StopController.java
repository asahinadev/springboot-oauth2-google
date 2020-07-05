package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/watch")
public class StopController extends BaseController<Void> {

	public StopController() {
		super(Void.class);
	}

	private static final String PATH = "users/{userId}/watch";

	@PostMapping
	public Mono<Void> post(
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client

	) {
		return post(client, PATH, varsMe);
	}

}
