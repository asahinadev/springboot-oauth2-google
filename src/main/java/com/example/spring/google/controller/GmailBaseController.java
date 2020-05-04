package com.example.spring.google.controller;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GmailBaseController {

	@Autowired
	WebClient gmailClient;

	protected Consumer<Map<String, Object>> oauth2AuthorizedClient(OAuth2AuthorizedClient client) {
		return ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client);
	}

	public ResponseSpec getResponse(
			OAuth2AuthorizedClient client,
			String path,
			Map<String, Object> uriVariables,
			MultiValueMap<String, String> params) {

		return gmailClient.get()
				.uri(b -> b.path(path).replaceQueryParams(params).build(uriVariables))
				.attributes(oauth2AuthorizedClient(client))
				.retrieve();
	}

	public <T> Flux<T> getFlux(
			OAuth2AuthorizedClient client,
			String path,
			Map<String, Object> uriVariables,
			MultiValueMap<String, String> params,
			Class<T> clazz) {

		return getResponse(client, path, uriVariables, params).bodyToFlux(clazz);
	}

	public <T> Flux<T> getFlux(
			OAuth2AuthorizedClient client, String path, Map<String, Object> uriVariables, Class<T> clazz) {
		return this.getFlux(client, path, uriVariables, emptyParams(), clazz);
	}

	public <T> Flux<T> getFlux(
			OAuth2AuthorizedClient client, String path, MultiValueMap<String, String> params, Class<T> clazz) {
		return this.getFlux(client, path, emptyUriValiables(), params, clazz);
	}

	public <T> Flux<T> getFlux(
			OAuth2AuthorizedClient client, String path, Class<T> clazz) {
		return this.getFlux(client, path, emptyUriValiables(), emptyParams(), clazz);
	}

	public <T> Mono<T> getMono(
			OAuth2AuthorizedClient client,
			String path,
			Map<String, Object> uriVariables,
			MultiValueMap<String, String> params,
			Class<T> clazz) {

		return getResponse(client, path, uriVariables, params).bodyToMono(clazz);
	}

	public <T> Mono<T> getMono(
			OAuth2AuthorizedClient client, String path, Map<String, Object> uriVariables, Class<T> clazz) {
		return this.getMono(client, path, uriVariables, emptyParams(), clazz);
	}

	public <T> Mono<T> getMono(
			OAuth2AuthorizedClient client, String path, MultiValueMap<String, String> params, Class<T> clazz) {
		return this.getMono(client, path, emptyUriValiables(), params, clazz);
	}

	public <T> Mono<T> getMono(
			OAuth2AuthorizedClient client, String path, Class<T> clazz) {
		return this.getMono(client, path, emptyUriValiables(), emptyParams(), clazz);
	}

	protected Map<String, Object> emptyUriValiables() {
		return Collections.emptyMap();
	}

	protected MultiValueMap<String, String> emptyParams() {
		return new LinkedMultiValueMap<>(0);
	}
}
