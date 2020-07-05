package com.example.spring.google.dto.gmail.values;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HistoryTypes {

	labelAdded,
	labelRemoved,
	messageAdded,
	messageDeleted;

	@JsonValue
	@Override
	public String toString() {
		return name();
	}
}