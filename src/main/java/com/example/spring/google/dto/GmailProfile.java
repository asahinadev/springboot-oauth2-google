package com.example.spring.google.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class GmailProfile {

	String emailAddress;

	Long messagesTotal;

	Long threadsTotal;

	Long historyId;

}
