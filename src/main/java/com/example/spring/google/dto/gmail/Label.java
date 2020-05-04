package com.example.spring.google.dto.gmail;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class Label {
	String id;
	String name;
	String messageListVisibility;
	String labelListVisibility;
	String type;
	long messagesTotal;
	long messagesUnread;
	long threadsTotal;
	long threadsUnread;
	LabelColor color;

	@JsonAnySetter
	Map<String, Object> any;
}
