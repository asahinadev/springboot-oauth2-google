package com.example.spring.google.dto.gmail;

import java.util.Map;

import com.example.spring.google.values.SafeColor;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class LabelColor {

	@JsonDeserialize(contentUsing = SafeColor.Deserializer.class)
	SafeColor textColor;

	@JsonDeserialize(contentUsing = SafeColor.Deserializer.class)
	SafeColor backgroundColor;

	@JsonAnySetter
	Map<String, Object> any;
}
