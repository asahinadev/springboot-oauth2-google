package com.example.spring.google.dto.gmail;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = false)
public class ProfileResponse {

	String emailAddress;
	Long messagesTotal;
	Long threadsTotal;
	Long historyId;

	@JsonAnySetter
	Map<String, Object> any;
}
