package com.example.spring.google.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class GmailMessages {
	List<GmailMessage> messages;
	String nextPageToken;
	Long resultSizeEstimate;
}
