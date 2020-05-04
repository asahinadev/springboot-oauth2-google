package com.example.spring.google.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class GmailMessage {
	String id;
	String threadId;
	List<String> labelIds;
	String snippet;
	Long historyId;
	Long internalDate;
	Map<String, Object> payload;

//"": {
//		    "partId": string,
//		    "mimeType": string,
//		    "filename": string,
//		    "headers": [
//		      {
//		        "name": string,
//		        "value": string
//		      }
//		    ],
//		    "body": users.messages.attachments Resource,
//		    "parts": [
//		      (MessagePart)
//		    ]
//		  }

	Long sizeEstimate;
	Long raw;
}
