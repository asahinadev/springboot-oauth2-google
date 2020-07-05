package com.example.spring.google.dto.gmail.values;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LabelFilterAction {
	exclude,
	@JsonEnumDefaultValue
	include;

	@JsonValue
	@Override
	public String toString() {
		return name();
	}
}
