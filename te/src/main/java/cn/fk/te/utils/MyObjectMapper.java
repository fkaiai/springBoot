package cn.fk.te.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;


/**
 * 自定义ObjectMapper
 * Created by xianlin.tan@bqjr.cn on 17-5-24.
 */
public class MyObjectMapper extends ObjectMapper {
    public final static class Holder{
        public final static MyObjectMapper MAPPER=new MyObjectMapper();
    }
    MyObjectMapper() {
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
