package com.example.spring.google.controller.gmail;

import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Profile;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/profile")
public class ProfileController extends BaseController<Profile> {

	public ProfileController() {
		super(Profile.class);
	}

	private static final String PATH = "users/{userId}/profile";

	private static final Map<String, Object> me = Map.of("userId", "me");

	@GetMapping
	public Mono<Profile> profile(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return get(client, PATH, me);
	}

}
