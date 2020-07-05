package com.example.spring.google.dto.gmail;

import com.example.spring.google.dto.gmail.values.LabelListVisibility;
import com.example.spring.google.dto.gmail.values.MessageListVisibility;
import com.example.spring.google.dto.gmail.values.SafeColor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = false)
public class LabelRequest {
	public static class Color {
		SafeColor textColor;
		SafeColor backgroundColor;
	}

	String name;
	MessageListVisibility messageListVisibility;
	LabelListVisibility labelListVisibility;
	Color color;
}
