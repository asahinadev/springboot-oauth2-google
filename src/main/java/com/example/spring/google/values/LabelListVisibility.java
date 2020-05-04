package com.example.spring.google.values;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public enum LabelListVisibility {

	HIDE("labelHide"),
	SHOW("labelShow"),
	UNREAD("labelShowIfUnread");

	private final String value;
	private static final Map<String, LabelListVisibility> map;
	static {
		Map<String, LabelListVisibility> temp = new HashMap<>();

		for (LabelListVisibility visibility : values()) {
			temp.put(visibility.getValue(), visibility);
		}

		map = Collections.unmodifiableMap(temp);
	}

	private LabelListVisibility(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public static class Deserializer extends JsonDeserializer<LabelListVisibility> {

		@Override
		public LabelListVisibility deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {

			String code = p.getText();

			if (map.containsKey(code)) {
				return map.get(code);
			}

			return SHOW;
		}

	}
}
