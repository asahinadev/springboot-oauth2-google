package com.example.spring.google.oauth2;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class Oauth2RestTemplate {

	final RestTemplate restTemplate;
	final Oauth2TokenService tokenService;

	@Autowired
	public Oauth2RestTemplate(Oauth2TokenService tokenService) {
		this.tokenService = tokenService;
		restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
	}

	public <E> E get(String url, Class<E> clazz) {
		return get(url, Collections.emptyMap(), new LinkedMultiValueMap<>(0), clazz);
	}

	public <E> E get(String url, Map<String, Object> uriVariables, Class<E> clazz) {
		return get(url, uriVariables, new LinkedMultiValueMap<>(0), clazz);
	}

	public <E> E get(String url, MultiValueMap<String, String> parameters, Class<E> clazz) {
		return get(url, Collections.emptyMap(), parameters, clazz);
	}

	public <E> E get(String url, Map<String, Object> uriVariables, MultiValueMap<String, String> parameters,
			Class<E> clazz) {

		URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParams(parameters).build(uriVariables);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(tokenService.getAccessToken());
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		RequestEntity<?> request = new RequestEntity<>(headers, HttpMethod.GET, uri);

		return restTemplate.exchange(request, clazz).getBody();
	}

}
