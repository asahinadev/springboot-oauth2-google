package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.LabelResponse;
import com.example.spring.google.dto.gmail.WatchRequest;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/labels")
public class LabelController extends BaseController<LabelResponse> {

	public LabelController() {
		super(LabelResponse.class);
	}

	private static final String PATH = "users/{userId}/labels";

	@GetMapping
	public Mono<LabelResponse> list(
			@RequestBody WatchRequest body,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client

	) {
		return post(client, PATH, varsMe, body);
	}

}
