package com.xixi.web4j.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 自定义的JSON转换MAPPER
 * @author xixi
 * @date 2013-6-24
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper(){
		super();
		//设置null转换""
		getSerializerProvider().setNullValueSerializer(new NullSerializer());
		//设置日期转换yyyy-MM-dd HH:mm:ss
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	//null的JSON序列
	private class NullSerializer extends JsonSerializer<Object> {
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			jgen.writeString("");
		}
	}
}
