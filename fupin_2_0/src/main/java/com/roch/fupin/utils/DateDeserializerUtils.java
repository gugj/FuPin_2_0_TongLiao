package com.roch.fupin.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 日期序列化实用工具类
 * @author wf
 *
 */
public class DateDeserializerUtils implements JsonDeserializer<Date> {

	@Override
	public java.util.Date deserialize(JsonElement json, Type type,

	JsonDeserializationContext context) throws JsonParseException {

		return new java.util.Date(json.getAsJsonPrimitive().getAsLong());

	}

}
