package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.ProfileResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/profile")
public class ProfileController extends BaseController<ProfileResponse> {

	public ProfileController() {
		super(ProfileResponse.class);
	}

	private static final String PATH = "users/{userId}/profile";

	@GetMapping
	public Mono<ProfileResponse> get(
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client

	) {
		return get(client, PATH, varsMe);
	}

}
