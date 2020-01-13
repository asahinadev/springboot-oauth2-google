package com.example.spring.google.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.google.dto.GmailProfile;
import com.example.spring.google.oauth2.Oauth2RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/gmail/")
public class GmailApiController {

	protected final String api = "https://content.googleapis.com/gmail/v1";

	final Oauth2RestTemplate restTemplate;

	@Autowired
	public GmailApiController(Oauth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("profile")
	@ResponseBody
	public GmailProfile profile(@AuthenticationPrincipal OAuth2User user) {

		String email = (String) user.getAttributes().get("email");
		log.debug("profile => {}", email);

		Map<String, Object> parames = new LinkedHashMap<>();
		parames.put("userId", email);

		return restTemplate.get(api + "/users/{userId}/profile", parames, GmailProfile.class);
	}

}
