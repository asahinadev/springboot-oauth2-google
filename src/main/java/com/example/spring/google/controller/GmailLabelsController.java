package com.example.spring.google.controller;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/gmail")
public class GmailLabelsController
		extends GmailBaseController {

	protected static final String LS_URL = "users/{userId}/labels";
	protected static final String ID_URL = "users/{userId}/labels/{labelId}";

	@GetMapping("/labels")
	public Mono<Labels> list(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return getMono(client, LS_URL, pathValue(null), Labels.class);
	}

	@GetMapping("/labels/{labelId}")
	public Mono<Label> get(@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return getMono(client, ID_URL, pathValue(labelId), Label.class);
	}

	@PostMapping("/labels")
	public Mono<Labels> create(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return postMono(client, LS_URL, pathValue(null), body, Labels.class);
	}

	@PutMapping("/labels/{labelId}")
	public Mono<Labels> put(@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return putMono(client, ID_URL, pathValue(labelId), body, Labels.class);
	}

	@PatchMapping("/labels/{labelId}")
	public Mono<Labels> patch(@PathVariable("labelId") String labelId,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
			@RequestBody Label body) {
		return patchMono(client, ID_URL, pathValue(labelId), body, Labels.class);
	}

	@DeleteMapping("/labels/{labelId}")
	public Mono<Void> delete(
			@PathVariable("labelId") String labelId,
			@AuthenticationPrincipal Mono<OAuth2User> oauth2User,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return deleteMono(client, ID_URL, pathValue(labelId));
	}

	protected Map<String, Object> pathValue(String labelId) {
		Map<String, Object> pathValue = new HashMap<>();
		pathValue.put("userId", "me");
		if (labelId != null)
			pathValue.put("labelId", labelId);
		return pathValue;
	}

}
