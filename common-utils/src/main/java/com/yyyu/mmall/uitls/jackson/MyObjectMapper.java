package com.yyyu.mmall.uitls.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/26.
 */
public class MyObjectMapper extends ObjectMapper{

    public MyObjectMapper(){
        super();
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
                jg.writeString("");
            }
        });
        //1.未加@jsonView注解的序列化、2.加了注解并对应上的才序列化（****）
        this.configure(MapperFeature.DEFAULT_VIEW_INCLUSION , true);
    }

}
