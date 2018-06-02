package cn.fk.te.utils.http;


import com.alibaba.fastjson.JSONObject;
import com.bqjr.cw.utils.JsonUtil;
import com.bqjr.cw.utils.Pair;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import static com.bqjr.cw.utils.Variable.*;
import static java.lang.String.format;

/**
 * http请求 bio模式,有性能问题,
 * 不用apache http client通用性更好,并且有利于开发人员了解http协议
 * Created by tanxianlin@lakala.com at 2016/12/14
 */
public class HttpUtil {
    public static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private enum RequestProperty {
        URLENCODED("application/x-www-form-urlencoded"),
        JSON("application/json"),
        FILE("multipart/form-data; boundary="+BOUNDARY),
        XML("application/xml"),
        SOAPXML("application/soap+xml")
        ;
        private String v;
        RequestProperty(String v) {
            this.v=v;
        }

        public String getV() {
            return v;
        }
        @Override
        public String toString(){
            return v;
        }
    }
    private enum Method{
        GET,PUT,POST,;
    }

    /**
     * httpGet
     * @param uri 请求的资源(包含参数)
     * @return
     */
    public  static Pair<Integer, String> get_(String uri , Map<String,String> cookies)  {
        return httpMethod(uri, Method.GET, null, RequestProperty.URLENCODED,cookies,false);
    }

    /**
     * httpPost
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> post(String url, Map<String, Object> params,Map<String,String> cookies )  {
        String queryString = getQueryString(params);

        queryString = queryString==null?"":queryString;

        return httpMethod(url, Method.POST, queryString.getBytes(CHARSET), RequestProperty.URLENCODED,cookies,false);
    }

    /**
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     */
    public static Pair<Integer,String> postXml(String url,String xml,Map<String,String> cookies)  {
        return httpMethod(url, Method.POST,xml.getBytes(CHARSET), RequestProperty.XML,cookies,false);
    }
    /**
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static Pair<Integer,String> postJson(String url,String json,Map<String,String> cookies) {
        return httpMethod(url, Method.POST,json.getBytes(CHARSET), RequestProperty.JSON,cookies,false);
    }

    /**
     * httpGet
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> get(String url, Map<String, Object> params,Map<String,String> cookies)  {
        return getOrPut(url, Method.GET, params, cookies);
    }
    public static  <T> T  get(String url, Map<String, Object> params,TypeReference<T> tr) {
        Pair<Integer,String> pair=  get(url,params);
        String json  = pair.value;
        return JsonUtil.toObject( json , tr );
    }
    /**
     *
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> put(String url, Map<String, Object> params,Map<String,String> cookies) {
        return getOrPut(url, Method.PUT, params,cookies);
    }

    private  static Pair<Integer, String> getOrPut(String url,Method method,Map<String,Object> params,Map<String,String> cookies)  {
        if(params!=null&&params.size()!=0){
            url = format("%s?%s", url, getQueryString(params));
        }
        return httpMethod(url, method, null, RequestProperty.URLENCODED, cookies,false);
    }


    /**
     * httpGet
     * @param uri 请求的资源(包含参数)
     * @return
     */
    public  static Pair<Integer, String> get(String uri )  {
        return httpMethod(uri, Method.GET, null, RequestProperty.URLENCODED,null,false);
    }

    /**
     * httpPost
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> post(String url, Map<String, Object> params)  {
        String queryString = getQueryString(params);
        queryString = queryString==null?"":queryString;
        return httpMethod(url, Method.POST, queryString.getBytes(CHARSET), RequestProperty.URLENCODED,null,false);
    }
    public static  <T> T  post(String url, Map<String, Object> params,TypeReference<T> tr) {
        Pair<Integer,String> pair=  post(url,params);
        String json  = pair.value;
        return JsonUtil.toObject( json , tr );
    }
    /**
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     */
    public static Pair<Integer,String> postXml(String url, String xml)  {
        return httpMethod(url, Method.POST,xml.getBytes(CHARSET), RequestProperty.XML,null,false);
    }
    public static Pair<Integer,String> postSOAPXml(String url,String xml)  {
        return httpMethod(url, Method.POST,xml.getBytes(CHARSET), RequestProperty.SOAPXML,null,false);
    }
    /**
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static Pair<Integer,String> postJson(String url,String json)  {
        return httpMethod(url, Method.POST,json.getBytes(CHARSET), RequestProperty.JSON,null,false);
    }


    /**
     * httpPut
     * @param uri 请求的资源(包含参数)
     * @return
     */
    public  static Pair<Integer, String> put(String uri) {
        return httpMethod(uri, Method.PUT,null, RequestProperty.URLENCODED,null,false);
    }

    /**
     * httpGet
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> get(String url, Map<String, Object> params)  {
        return getOrPut(url, Method.GET, params, null);
    }

    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream=null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
            System.out.println("Weixin server connection timed out");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("http request error:{}");
        }finally{
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     *
     * @param url 地址
     * @param params 参数
     * @return
     */
    public  static Pair<Integer, String> put(String url, Map<String, Object> params) {
        return getOrPut(url, Method.PUT, params,null);
    }
    private  static Pair<Integer, String> getOrPut(String url,Method method,Map<String,Object> params)  {
        return httpMethod(format("%s?%s", url, getQueryString(params)), method, null, RequestProperty.URLENCODED, null,false);
    }


    public static <T> T get( String uri , Class<T> clazz) {
        return JsonUtil.toObject(get(uri).value,clazz);
    }
    public static <T> T get( String uri ,TypeReference<T> tr) {
        return JsonUtil.toObject(get(uri).value,tr);
    }
    public static <T> T get(BaseRequest baseRequest , Class<T> clazz ) {
        baseRequest.build();
        Pair<Integer, String> pair = get(baseRequest.url +baseRequest.getUri() , baseRequest.param);
        String json  = pair.value;
        return JsonUtil.toObject(json, clazz);
    }
    public static <T> T get( BaseRequest baseRequest ,TypeReference<T> tr) {
        baseRequest.build();
        Pair<Integer, String> pair = get(baseRequest.url + baseRequest.getUri(), baseRequest.param);
        String json  = pair.value;
        return JsonUtil.toObject( json , tr );
    }
    public static  <T> T  post(BaseRequest baseRequest ,Class<T> clazz) {
        baseRequest.build();
        Pair<Integer, String> pair = post(baseRequest.url +baseRequest.getUri(), baseRequest.param);
        String json  = pair.value;
        return JsonUtil.toObject( json , clazz );
    }
    public static Pair<Integer, String> post(BaseRequest baseRequest) {
        baseRequest.build();
        return  post(baseRequest.url +baseRequest.getUri(), baseRequest.param);
    }

    public static  <T> T  post(BaseRequest baseRequest ,TypeReference<T> tr) {
        baseRequest.build();
        Pair<Integer,String> pair=  post(baseRequest.url +baseRequest.getUri(), baseRequest.param);
        String json  = pair.value;
        return JsonUtil.toObject( json , tr );
    }
    public static <T> T upload(String uri, Map<String,String> params, Class<T> clazz,Object... files){
        return JsonUtil.toObject(upload(uri,params,files).value,clazz);
    }
    public static Pair<Integer, String> upload(String uri, Map<String,String> params, Object... files){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        StringBuilder sb=new StringBuilder();
        try {
            if(params!=null)for(Map.Entry entry:params.entrySet()){
                sb.append(BOUNDARY_LINE)
                        .append(PARAM_NAME_PREFIX).append(entry.getKey()).append("\"").append(PARAM_NAME_SUFFIX)
                        .append(URLEncoder.encode((String) entry.getValue(), CHARSET_STR)).append(PARAM_VALUE_SUFFIX);
            }
            if(sb.length()!=0){
//            System.out.println(sb.toString());
                toBytes(bos, sb);
            }
            if(files!=null){
                for(Object o:files){
                    UploadFile file=(UploadFile)o;
                    String filename = file.getFilename();
                    if(filename==null){
                        filename=File.separator+file.getName();
                    }
                    sb.append(BOUNDARY_LINE)
                            .append(PARAM_NAME_PREFIX)
                            .append(format("%s\";filename=\"%s\"\r%n", file.getName(), filename))
                            .append(format("Content-Type:%s", file.getType()))
                            .append(PARAM_NAME_SUFFIX);
//            System.out.println(sb.toString());
                    toBytes(bos, sb);
                    bos.write(file.getData());
                    bos.write(PARAM_VALUE_SUFFIX_BYTES);
                }
            }
            bos.write(CONTENT_END);
            bos.flush();
        } catch (Exception e) {
            throw new RuntimeException("http请求出错",e);
        }
        byte[] postData=bos.toByteArray();
        return  httpMethod(uri, Method.POST,postData, RequestProperty.FILE,null,false);

    }
    private static void toBytes(ByteArrayOutputStream bos, StringBuilder sb) throws IOException {
        bos.write(sb.toString().getBytes(CHARSET));
        sb.delete(0,sb.length());
    }
    public static byte[] download(String uri){
        return (byte[]) httpMethod(uri, Method.GET,null, RequestProperty.URLENCODED,null,true).value;
    }
    private static final int BUFFER_SIZE=64;
    public static Pair httpMethod(String uri,Method method,byte[] postData, RequestProperty requestProperty,Map<String,String> cookies,boolean isByte) {
//        if(postData!=null&&postData.length<2000){
//            logger.info("http {}->uri:\n{}?{}",method,uri,new String(postData,CHARSET));
//        }else{
//            logger.info("http {}->uri:{}",method,uri);
//        }

        try{
            HttpURLConnection con= (HttpURLConnection) (new URL(uri)).openConnection();
            con.setRequestMethod(method.toString());
            OutputStream out;

            if(Method.POST.equals(method)&&postData!=null){
                con.setDoOutput(true);

                con.setRequestProperty("Content-Type",  requestProperty.toString()
                        +";charset="+CHARSET_STR
                );
                con.setRequestProperty("Content-length", String.valueOf(postData.length));
                out=con.getOutputStream();

                out.write(postData);
                out.flush();
                out.close();
            }
            InputStream in = con.getInputStream();
            if (in==null) {
                in = con.getErrorStream();
            }
            if(isByte){
                byte[] buffer=new byte[BUFFER_SIZE];
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int len;
                while ((len=in.read(buffer))>0) {
                    bo.write(buffer,0,len);
                }
                bo.flush();
                in.close();
                return new Pair<>(con.getResponseCode(), bo.toByteArray());
            }else{
                BufferedReader bin = new BufferedReader(new InputStreamReader(in, CHARSET));
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = bin.readLine()) != null){
                    buffer.append(line).append("\r\n");
                }
                in.close();
                bin.close();
                Pair<Integer, String> pair = new Pair<>(con.getResponseCode(), buffer.toString());
//                logger.info(JsonUtil.toJson(pair));
                return pair;
            }
        }catch (IOException e){
            throw new RuntimeException("http请求出错",e);

        }
    }

    public static String getQueryString(Map<String,Object> params) {
        if(params==null)return null;

        String q = "";
        for (Map.Entry param : params.entrySet()) {
            try {
                String coded = URLEncoder.encode(param.getValue().toString(), CHARSET_STR);
                q += format("%s=%s&", param.getKey(), coded);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("http请求出错",e);
            }
        }
        return q.substring(0, q.length() - 1);
    }

    public static String getCookie( String cookieStr ,String key ){
        if( StringUtils.isEmpty(cookieStr) ) {
            return  null;
        }
        String [] cookies = cookieStr.split(";");
        for ( String cookie : cookies ) {
            String [] c = cookie.split("=");
            if( c[0].trim().equals( key )) {
                try {
                    return URLDecoder.decode(c[1], CHARSET_STR);
                } catch (UnsupportedEncodingException e) {
                    log.error("",e);
                    return null;
                }
            }
        }
        return  null;
    }

    /**
     * 用于请求未信任https,使用方法,在调用https请求前运行此函数
     */
    public static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("",e);
        }
    }

    private static final String BOUNDARY="WebKitFormBoundary7PR1aLrMtsmdeGtB";
    //    private static final String BOUNDARY_LINE= format("--%s\n",BOUNDARY);
    private static final String BOUNDARY_LINE= format("--%s\r%n",BOUNDARY);

    private static final String PARAM_NAME_PREFIX ="Content-Disposition:form-data;name=\"";
    private static final String PARAM_NAME_SUFFIX ="\r\n\r\n";
    private static final String PARAM_VALUE_SUFFIX ="\r\n";
    private static final byte[] PARAM_VALUE_SUFFIX_BYTES ="\r\n".getBytes(CHARSET);
    private static  byte[] CONTENT_END= format("\r%n--%s--\r%n",BOUNDARY).getBytes(CHARSET);

    public static class UploadFile{

        public enum  ContentType{
            JPG("image/jpeg"),
            //            GIF("image/gif"),
            TXT("text/plain"),
            XML("text/xml"),
            DOC("application/wps-office.doc"),
            EXCEL("application/vnd.ms-excel"),
            ;
            private String type;
            ContentType(String type) {
                this.type=type;
            }
            @Override
            public String toString(){
                return type;
            }
        }
        private String name;
        private String filename;
        private ContentType type;
        private byte[] data;


        public UploadFile(String name, String filename, ContentType type, byte[] data) {
            this.name = name;
            this.filename = filename;
            this.type = type;
            this.data = data;
        }


        public String getName() {
            return name;
        }

        public String getFilename() {
            return filename;
        }

        public ContentType getType() {
            return type;
        }

        public byte[] getData() {
            return data;
        }

        public UploadFile setName(String name) {
            this.name = name;
            return this;
        }

        public UploadFile setFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public UploadFile setType(ContentType type) {
            this.type = type;
            return this;
        }

        public UploadFile setData(byte[] data) {
            this.data = data;
            return this;
        }
    }


}
