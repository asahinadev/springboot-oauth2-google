package com.example.spring.google.controller.gmail;

import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.google.dto.gmail.Messages;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gmail/messages")
public class MessagesController extends BaseController<Messages> {

	private static final String PATH = "users/{userId}/messages";

	public MessagesController() {
		super(Messages.class);
	}

	@GetMapping
	public Mono<Messages> messages(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		return get(client, PATH, Map.of("userId", "me"));
	}

}
