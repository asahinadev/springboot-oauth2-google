package com.example.spring.google.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.google.dto.GmailProfile;
import com.example.spring.google.dto.GmailStop;
import com.example.spring.google.dto.GmailWatchRequest;
import com.example.spring.google.dto.GmailWatchResponse;
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

	@GetMapping("stop")
	@ResponseBody
	public GmailStop stop(@AuthenticationPrincipal OAuth2User user) {

		String email = (String) user.getAttributes().get("email");
		log.debug("profile => {}", email);

		Map<String, Object> parames = new LinkedHashMap<>();
		parames.put("userId", email);

		return restTemplate.post(api + "/users/{userId}/stop", parames, GmailStop.class);
	}

	@GetMapping("watch")
	@ResponseBody
	public GmailWatchResponse watch(@AuthenticationPrincipal OAuth2User user, GmailWatchRequest request) {

		String email = (String) user.getAttributes().get("email");
		log.debug("profile => {}", email);

		Map<String, Object> parames = new LinkedHashMap<>();
		parames.put("userId", email);

		MultiValueMap<String, String> postData = new LinkedMultiValueMap<>();

		postData.add("topicName", request.getTopicName());
		postData.add("labelFilterAction", request.getLabelFilterAction());
		postData.addAll("labelIds", request.getLabelIds());

		return restTemplate.post(api + "/users/{userId}/watch", parames, postData, GmailWatchResponse.class);
	}

}
