package com.example.spring.google.dto.gmail.values;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public enum LabelListVisibility {
	hide,
	@JsonDeserialize
	show;

	@JsonValue
	@Override
	public String toString() {
		return name();
	}
}