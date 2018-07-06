package cn.fk.te.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xianlin.tan@bqjr.cn on 17-5-24.
 */
public  class JsonUtil {
    protected static final ObjectMapper mapper =new MyObjectMapper();

    /**
     * @param o 被转换对象
     * @return 字符串
     * @throws JsonProcessingException
     */
    public static <T> String toJson(T o) {
        try {

            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {

            throw new RuntimeException("实体转换成json出错",e);
        }
    }
    /**
     JSON串转换为Java泛型对象
     * @param s JSON字符串
     * @param tr TypeReference,例如: new TypeReference< List<User> >(){}
     * @return List对象列表
     */
    public static <T> T toObject(String s, TypeReference<T> tr) {
        try {
            return mapper.readValue(s, tr);
        } catch (IOException e) {
            throw new RuntimeException("json转换成实体出错",e);
        }
    }

    /**
     非泛型转换
     */
    public static <T> T toObject(String s, Class<T> tClass) {
        try {
            return mapper.readValue(s,tClass);
        } catch (IOException e) {
            throw new RuntimeException("json转换成实体出错",e);
        }
    }

    /*
     *将post请求的json字符串格式的参数转为JSONObject
     */
    public static JSONObject httpProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        if(sb.toString().length()<=0){
            return null;
        }else {
            return new JSONObject(sb.toString());
        }
    }


    public static Map<String,Object> Obj2Map(Object obj) throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
    public Object map2Obj(Map<String,Object> map,Class<?> clz) throws Exception{
        Object obj = clz.newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }
}
