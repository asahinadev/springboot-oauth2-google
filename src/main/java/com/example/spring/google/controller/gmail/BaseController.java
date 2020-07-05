package com.example.spring.google.controller.gmail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseController<T> {

	protected static final Map<String, Object> varsEmpty = Collections.emptyMap();
	protected static final Map<String, Object> varsMe = Map.of("userId", "me");

	protected static Map<String, Object> label(String id) {
		Assert.notNull(id, "id");
		Map<String, Object> vars = new HashMap<>(varsMe);
		vars.put("labelId", id);
		return vars;
	}

	protected static final LinkedMultiValueMap<String, String> paramEmpty = new LinkedMultiValueMap<>(0);

	@Autowired
	protected WebClient gmail;

	Class<T> clazz;

	public BaseController(Class<T> clazz) {
		Assert.notNull(clazz, "clazz");
		this.clazz = clazz;
	}

	protected Consumer<Map<String, Object>> attributes(OAuth2AuthorizedClient client) {
		Assert.notNull(client, "client");
		return ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client);
	}

	/**
	 * GET アクセス.
	 * 
	 * @param client
	 *                        {@link OAuth2AuthorizedClient}
	 * @param path
	 *                        {@link String}
	 * @param vars
	 *                        {@link Map}
	 * @param queryParams
	 *                        {@link Map}
	 * 
	 * @return {@link WebClient.ResponseSpec}
	 */
	protected WebClient.ResponseSpec getResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> vars, MultiValueMap<String, String> queryParams) {
		return gmail.get()
				.uri(b -> b.path(path).replaceQueryParams(queryParams).build(vars))
				.attributes(attributes(client)).retrieve();
	}

	/**
	 * POST アクセス.
	 * 
	 * @param client
	 *                   {@link OAuth2AuthorizedClient}
	 * @param path
	 *                   {@link String}
	 * @param vars
	 *                   {@link Map}
	 * @param body
	 *                   {@link Object}
	 * 
	 * @return {@link WebClient.ResponseSpec}
	 */
	protected WebClient.ResponseSpec postResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {
		return gmail.post()
				.uri(b -> b.path(path).build(vars))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	/**
	 * PATCH アクセス.
	 * 
	 * @param client
	 *                   {@link OAuth2AuthorizedClient}
	 * @param path
	 *                   {@link String}
	 * @param vars
	 *                   {@link Map}
	 * @param body
	 *                   {@link Object}
	 * 
	 * @return {@link WebClient.ResponseSpec}
	 */
	protected WebClient.ResponseSpec patchResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {
		return gmail.patch()
				.uri(b -> b.path(path).build(vars))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	/**
	 * PUT アクセス.
	 * 
	 * @param client
	 *                   {@link OAuth2AuthorizedClient}
	 * @param path
	 *                   {@link String}
	 * @param vars
	 *                   {@link Map}
	 * @param body
	 *                   {@link Object}
	 * 
	 * @return {@link WebClient.ResponseSpec}
	 */
	protected WebClient.ResponseSpec putResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {

		return gmail.put()
				.uri(b -> b.path(path).build(vars))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	/**
	 * DELETE アクセス.
	 * 
	 * @param client
	 *                   {@link OAuth2AuthorizedClient}
	 * @param path
	 *                   {@link String}
	 * @param vars
	 *                   {@link Map}
	 * @param body
	 *                   {@link Object}
	 * 
	 * @return {@link WebClient.ResponseSpec}
	 */
	protected WebClient.ResponseSpec deleteResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> vars) {
		return gmail.delete().uri(b -> b.path(path).build(vars)).attributes(attributes(client)).retrieve();
	}

	public Flux<T> flux(OAuth2AuthorizedClient client, String path, Map<String, Object> vars, MultiValueMap<String, String> params) {
		return getResponse(client, path, vars, params).bodyToFlux(clazz);
	}

	public Flux<T> getFlux(OAuth2AuthorizedClient client, String path, Map<String, Object> vars) {
		return this.flux(client, path, vars, paramEmpty);
	}

	public Flux<T> flux(
			OAuth2AuthorizedClient client, String path, MultiValueMap<String, String> params) {
		return this.flux(client, path, varsEmpty, params);
	}

	public Flux<T> flux(OAuth2AuthorizedClient client, String path) {
		return this.flux(client, path, varsEmpty, paramEmpty);
	}

	public Mono<T> get(OAuth2AuthorizedClient client, String path, Map<String, Object> vars, MultiValueMap<String, String> params) {
		return getResponse(client, path, vars, params).bodyToMono(clazz);
	}

	public Mono<T> get(OAuth2AuthorizedClient client, String path, Map<String, Object> vars) {
		return this.get(client, path, vars, paramEmpty);
	}

	public Mono<T> get(OAuth2AuthorizedClient client, String path, MultiValueMap<String, String> params) {
		return this.get(client, path, varsEmpty, params);
	}

	public Mono<T> get(OAuth2AuthorizedClient client, String path) {
		return this.get(client, path, varsEmpty, paramEmpty);
	}

	public Mono<T> post(OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {
		return postResponse(client, path, vars, body).bodyToMono(clazz);
	}

	public Mono<T> post(OAuth2AuthorizedClient client, String path, Object body) {
		return this.post(client, path, varsEmpty, body);
	}

	public Mono<T> post(OAuth2AuthorizedClient client, String path) {
		return this.post(client, path, varsEmpty, null);
	}

	public Mono<T> patch(OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {
		return patchResponse(client, path, vars, body).bodyToMono(clazz);
	}

	public Mono<T> patch(OAuth2AuthorizedClient client, String path, Object body) {
		return this.patch(client, path, varsEmpty, body);
	}

	public Mono<T> put(OAuth2AuthorizedClient client, String path, Object body) {
		return this.put(client, path, varsEmpty, body);
	}

	public Mono<T> put(OAuth2AuthorizedClient client, String path, Map<String, Object> vars, Object body) {
		return putResponse(client, path, vars, body).bodyToMono(clazz);
	}

	public Mono<Void> delete(OAuth2AuthorizedClient client, String path, Map<String, Object> vars) {
		return deleteResponse(client, path, vars).bodyToMono(Void.class);
	}

	public Mono<Void> delete(OAuth2AuthorizedClient client, String path) {
		return deleteResponse(client, path, varsEmpty).bodyToMono(Void.class);
	}

}
