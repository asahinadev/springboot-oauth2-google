package com.example.spring.google.dto.gmail;

import com.example.spring.google.dto.gmail.values.HistoryTypes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = false)
public class HistoryListRequest {
	HistoryTypes historyTypes;
	String labelId;
	String pageToken;
	long maxResults = 50;
	long startHistoryId = 0;
}
