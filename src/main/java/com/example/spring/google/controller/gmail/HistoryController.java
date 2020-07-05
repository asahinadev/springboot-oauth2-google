package com.example.spring.google.controller.gmail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.HistoryListRequest;
import com.example.spring.google.dto.gmail.HistoryListResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/history")
public class HistoryController extends BaseController<HistoryListResponse> {

	public HistoryController() {
		super(HistoryListResponse.class);
	}

	private static final String PATH = "users/{userId}/history";

	@GetMapping
	public Mono<HistoryListResponse> get(
			HistoryListRequest request,
			@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client

	) {

		LinkedMultiValueMap<String, String> query = new LinkedMultiValueMap<>();
		query.set("labelId", request.getLabelId());
		query.set("pageToken", request.getPageToken());
		query.set("historyTypes", request.getHistoryTypes().name());
		query.set("maxResults", request.getMaxResults() + "");

		return get(client, PATH, varsMe, query);
	}

}
