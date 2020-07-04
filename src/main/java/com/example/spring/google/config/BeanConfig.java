package com.example.spring.google.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

	protected static final String GMAIL_API = "https://www.googleapis.com/gmail/v1/";

	@Autowired
	ReactiveClientRegistrationRepository clientRegistrationRepo;

	@Autowired
	ServerOAuth2AuthorizedClientRepository authorizedClientRepo;

	private ServerOAuth2AuthorizedClientExchangeFilterFunction filter() {
		return new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo, authorizedClientRepo);
	}

	@Bean
	public WebClient gmail() {
		return WebClient.builder().baseUrl(GMAIL_API).filter(filter()).build();
	}
}
