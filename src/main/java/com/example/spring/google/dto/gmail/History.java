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
public class History {
	Long id;
	List<Message> messages;
	List<HistoryIItem> messagesAdded;
	List<HistoryIItem> messagesDeleted;
	List<HistoryIItem> labelsAdded;
	List<HistoryIItem> labelsRemoved;

	@JsonAnySetter
	Map<String, Object> any;
}
