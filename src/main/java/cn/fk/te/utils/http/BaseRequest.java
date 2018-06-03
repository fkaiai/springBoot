package cn.fk.te.utils.http;

import cn.fk.te.utils.DateUtil;
import cn.fk.te.utils.JsonUtil;
import cn.fk.te.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * todo 做缓存
 * http请求数据基类
 * Created by tanxianlin@lakala.com at 2016/12/14
 */
public abstract  class BaseRequest {
    protected Logger log = LoggerFactory.getLogger(getClass());
    /**
     *
     * 地址
     *
     * */
    public String  url ;

    public BaseRequest(String url) {
        this.url = url;
    }
    /**
     *
     * 参数
     *
     * **/
    public Map<String,Object> param = new HashMap<String, Object>();


//    /**
//     *
//     * 参数转换
//     *
//     * */
//    public abstract Pair<String,Object> convert( Pair<String,Object> pair );

    public BaseRequest addParam(String name , String val) {
        param.put(name,val);
        return  this;
    }

    /**
     *
     * 参数处理
     *
     * 返回 一个 key=value  的list
     *
     * */
    public String getParamString() {
        StringBuilder paramString = new StringBuilder();
        if( param.size() ==0 ) {
            return "";
        }
        for (Map.Entry<String,Object> entry : param.entrySet())
        {
            String name = entry.getKey();
            Object value = entry.getValue();
            Pair<String,Object> pair = new Pair<>(name, value);
            paramString.append("&").append(  pair.key ).append("=").append( pair.value );
        }
        return  paramString.substring(1);
    }
    private void build(Class<?> requestClass){
        if(requestClass==BaseRequest.class)return;
        Field[] fields = requestClass.getDeclaredFields();
        build(requestClass.getSuperclass());
        if( fields == null ) return;
        for (Field field : fields) {
            try {
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                Object value=field.get(this);
                String name=field.getName();
                if(value==null)continue;
                Type type=field.getAnnotation(Type.class);

                if(type!=null){
                    switch (type.name()){
                        case "date": value= DateUtil.toShort((Date) value);break;
                        case "datetime": value= DateUtil.toLong((Date) value);break;
                        case "timstamp": value= DateUtil.toShort((Date) value);break;
                        case "json": value= JsonUtil.toJson(value);break;
                        default:throw new IllegalArgumentException("不支持该类型:"+type.name());
                    }
                }
                param.put( name , value );
            }
            catch (Exception e) {
                log.error("",e);
            }
        }

        for (Map.Entry<String,Object> entry : param.entrySet())
        {
            String name = entry.getKey();
            Object value = entry.getValue();
            Pair<String,Object> pair = new Pair<>(name, value);
//            pair = convert( pair );
            this.param.put( pair.key , pair.value);
        }
    }
    public void build() {
        build(this.getClass());
    }

    /**
     *
     * get uri
     *
     * */
    public abstract String getUri();

    @Override
    public String toString() {
        return this.url+this.getUri() +"?"+ getParamString();
    }
}
