package com.example.spring.google.dto.gmail;

import java.util.Map;

import com.example.spring.google.values.LabelListVisibility;
import com.example.spring.google.values.MessageListVisibility;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class Label {
	String id;
	String name;
	String type;
	LabelColor color;

	@JsonDeserialize(contentUsing = MessageListVisibility.Deserializer.class)
	MessageListVisibility messageListVisibility;

	@JsonDeserialize(contentUsing = LabelListVisibility.Deserializer.class)
	LabelListVisibility labelListVisibility;

	long messagesTotal;
	long messagesUnread;
	long threadsTotal;
	long threadsUnread;

	@JsonAnySetter
	Map<String, Object> any;
}
