package com.example.spring.google.dto.gmail;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = false)
public class WatchRequest {

	public static enum Filter {

		exclude,

		@JsonEnumDefaultValue
		include;

		@JsonValue
		@Override
		public String toString() {
			return name();
		}
	}

	List<String> labelIds;
	Filter labelFilterAction;
	String topicName;
}
