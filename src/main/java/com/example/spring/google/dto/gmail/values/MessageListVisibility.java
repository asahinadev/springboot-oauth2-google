package com.example.spring.google.dto.gmail.values;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public enum MessageListVisibility {
	labelHide,
	@JsonDeserialize
	labelShow,
	labelShowIfUnread;

	@JsonValue
	@Override
	public String toString() {
		return name();
	}
}
