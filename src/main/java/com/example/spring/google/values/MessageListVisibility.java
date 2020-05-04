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

public enum MessageListVisibility {

	HIDE("hide"),
	SHOW("show");

	private final String value;
	private static final Map<String, MessageListVisibility> map;
	static {
		Map<String, MessageListVisibility> temp = new HashMap<>();

		for (MessageListVisibility visibility : values()) {
			temp.put(visibility.getValue(), visibility);
		}

		map = Collections.unmodifiableMap(temp);
	}

	private MessageListVisibility(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public static class Deserializer extends JsonDeserializer<MessageListVisibility> {

		@Override
		public MessageListVisibility deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {

			String code = p.getText();

			if (map.containsKey(code)) {
				return map.get(code);
			}

			return SHOW;
		}

	}
}
