package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Label;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/labels")
public class LabelController extends BaseController<Label> {

	private static final String PATH = "users/{userId}/labels/{labelId}";

	public LabelController() {
		super(Label.class);
	}

	@GetMapping("{labelId}")
	public Mono<Label> get(
			@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return get(client, PATH, label(labelId));
	}

}
