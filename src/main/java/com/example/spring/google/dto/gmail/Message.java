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
public class Message {
	String id;
	String threadId;
	List<String> labelIds;
	String snippet;
	Long historyId;
	Long internalDate;
	MessagePayload payload;
	Long sizeEstimate;
	Long raw;

	@JsonAnySetter
	Map<String, Object> any;
}
