package com.example.spring.google.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class GmailWatchRequest {

	List<String> labelIds = new ArrayList<>();
	String labelFilterAction;
	String topicName;

}
