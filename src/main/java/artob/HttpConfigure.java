package artob;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class HttpConfigure {

    private static Map<String,Object> map = new HashMap<>();//创建map容器，同时声明参数值的变量名

    static CloseableHttpClient http(){
        CloseableHttpClient aDefault = HttpClients.createDefault(); //创建客户端
        return aDefault;
    }
//    post请求
    public  static HttpPost post(String url){
        HttpPost httpPost = new HttpPost(url);
        return httpPost;
    }
//    get请求
     static HttpGet get(String url){
        HttpGet httpGet = new HttpGet(url);
        return httpGet;
    }
//    请求入参
   static void addParameter(String key,Object value){
       map.put(key,value);
   }
//   请求头
    static void addHeader(String key,Object value){
        map.put(key,value);
    }
//   读取json 方便入参后拿到json值引用
    public static String getParametersAsJson() {
        JSONObject jsonObject = new JSONObject(map); // addParameter 方法给map容器传入了参数， JSONobject 获取json
        return jsonObject.toString(); //返回 string格式
    }
//    字符转换
    public  static  StringEntity  assembling() throws UnsupportedEncodingException {
        StringEntity stringEntity = new StringEntity(HttpConfigure.getParametersAsJson(),"utf-8");
        return stringEntity;
    }
//
    public static void ex(){
//        CloseableHttpResponse execute = http().execute(post());
    }
}

