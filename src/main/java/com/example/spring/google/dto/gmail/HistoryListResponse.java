package com.example.spring.google.dto.gmail;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = false)
public class HistoryListResponse {

	public static class Item {
		long id;
		Message message;
		List<Message> messagesAdded;
		List<Message> messagesDeleted;
		List<Message> labelsAdded;
		List<Message> labelsRemoved;
	}

	public static class Message {
		String message;
		List<String> labelIds;
	}

	long id;
	String nextPageToken;
	String historyId;
	List<Item> history;
}
