package com.example.spring.google.dto.gmail;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class MessagePayload {
	String partId;
	String mimeType;
	String filename;
	List<Map<String, String>> headers;
	Map<String, String> body;
	List<Map<String, String>> parts;

	@JsonAnySetter
	Map<String, Object> any;
}
