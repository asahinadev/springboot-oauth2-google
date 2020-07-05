package com.example.spring.google.dto.gmail.values;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public enum SafeColor {

	COLOR_001("#000000"), COLOR_002("#434343"), COLOR_003("#666666"), COLOR_004("#999999"), COLOR_005("#cccccc"),
	COLOR_006("#efefef"), COLOR_007("#f3f3f3"), COLOR_008("#ffffff"), COLOR_009("#fb4c2f"), COLOR_010("#ffad47"),
	COLOR_011("#fad165"), COLOR_012("#16a766"), COLOR_013("#43d692"), COLOR_014("#4a86e8"), COLOR_015("#a479e2"),
	COLOR_016("#f691b3"), COLOR_017("#f6c5be"), COLOR_018("#ffe6c7"), COLOR_019("#fef1d1"), COLOR_020("#b9e4d0"),
	COLOR_021("#c6f3de"), COLOR_022("#c9daf8"), COLOR_023("#e4d7f5"), COLOR_024("#fcdee8"), COLOR_025("#efa093"),
	COLOR_026("#ffd6a2"), COLOR_027("#fce8b3"), COLOR_028("#89d3b2"), COLOR_029("#a0eac9"), COLOR_030("#a4c2f4"),
	COLOR_031("#d0bcf1"), COLOR_032("#fbc8d9"), COLOR_033("#e66550"), COLOR_034("#ffbc6b"), COLOR_035("#fcda83"),
	COLOR_036("#44b984"), COLOR_037("#68dfa9"), COLOR_038("#6d9eeb"), COLOR_039("#b694e8"), COLOR_040("#f7a7c0"),
	COLOR_041("#cc3a21"), COLOR_042("#eaa041"), COLOR_043("#f2c960"), COLOR_044("#149e60"), COLOR_045("#3dc789"),
	COLOR_046("#3c78d8"), COLOR_047("#8e63ce"), COLOR_048("#e07798"), COLOR_049("#ac2b16"), COLOR_050("#cf8933"),
	COLOR_051("#d5ae49"), COLOR_052("#0b804b"), COLOR_053("#2a9c68"), COLOR_054("#285bac"), COLOR_055("#653e9b"),
	COLOR_056("#b65775"), COLOR_057("#822111"), COLOR_058("#a46a21"), COLOR_059("#aa8831"), COLOR_060("#076239"),
	COLOR_061("#1a764d"), COLOR_062("#1c4587"), COLOR_063("#41236d"), COLOR_064("#83334c"), COLOR_065("#464646"),
	COLOR_066("#e7e7e7"), COLOR_067("#0d3472"), COLOR_068("#b6cff5"), COLOR_069("#0d3b44"), COLOR_070("#98d7e4"),
	COLOR_071("#3d188e"), COLOR_072("#e3d7ff"), COLOR_073("#711a36"), COLOR_074("#fbd3e0"), COLOR_075("#8a1c0a"),
	COLOR_076("#f2b2a8"), COLOR_077("#7a2e0b"), COLOR_078("#ffc8af"), COLOR_079("#7a4706"), COLOR_080("#ffdeb5"),
	COLOR_081("#594c05"), COLOR_082("#fbe983"), COLOR_083("#684e07"), COLOR_084("#fdedc1"), COLOR_085("#0b4f30"),
	COLOR_086("#b3efd3"), COLOR_087("#04502e"), COLOR_088("#a2dcc1"), COLOR_089("#c2c2c2"), COLOR_090("#4986e7"),
	COLOR_091("#2da2bb"), COLOR_092("#b99aff"), COLOR_093("#994a64"), COLOR_094("#f691b2"), COLOR_095("#ff7537"),
	COLOR_096("#ffad46"), COLOR_097("#662e37"), COLOR_098("#ebdbde"), COLOR_099("#cca6ac"), COLOR_100("#094228"),
	COLOR_101("#42d692"), COLOR_102("#16a765"),
	;

	private final String color;
	private static final Map<String, SafeColor> map;
	static {
		Map<String, SafeColor> temp = new HashMap<>();

		for (SafeColor color : values()) {
			temp.put(color.getColor(), color);
		}

		map = Collections.unmodifiableMap(temp);
	}

	private SafeColor(String color) {
		this.color = color;
	}

	@JsonValue
	public String getColor() {
		return color;
	}

	public static class Deserializer extends JsonDeserializer<SafeColor> {

		@Override
		public SafeColor deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {

			String code = p.getText();

			if (map.containsKey(code)) {
				return map.get(code);
			}

			return COLOR_001;
		}

	}
}
