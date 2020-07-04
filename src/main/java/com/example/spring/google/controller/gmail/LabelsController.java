package com.example.spring.google.controller.gmail;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Label;
import com.example.spring.google.dto.gmail.Labels;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/labels")
public class LabelsController extends BaseController<Labels> {

	private static final String PATH = "users/{userId}/labels";
	private static final String PATH_ID = "users/{userId}/labels/{labelId}";

	public LabelsController() {
		super(Labels.class);
	}

	@GetMapping
	public Mono<Labels> list(
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return get(client, PATH, varsMe);
	}

	@PostMapping("/labels")
	public Mono<Labels> create(
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return post(client, PATH, varsMe, body);
	}

	@PutMapping("{labelId}")
	public Mono<Labels> put(@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return put(client, PATH_ID, label(labelId), body);
	}

	@PatchMapping("{labelId}")
	public Mono<Labels> patch(@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return patch(client, PATH_ID, label(labelId), body);
	}

	@DeleteMapping("{labelId}")
	public Mono<Void> delete(
			@PathVariable("labelId") String labelId,
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return delete(client, PATH_ID, label(labelId));
	}

}
