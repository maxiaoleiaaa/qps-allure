package artob;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
public class Blogin {
    //  登录 并获取7.bet登录token
    public static String betLogin() throws IOException {
        String url = "https://dev-api.coolgame.pro/login-api/v1/manager/auth/login";
        String json = "{\"account\": \"test012\", \"password\": \"aa123456\"}";
        if (JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data").get("token")!=null){
            return (String) JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data").get("token");
        }else{
            System.out.println("获取token失败："+JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data"));
        }
        return null;
    }

    //  登录 并获取artoken
    public static String arLogin() throws IOException {
        String url = "https://dev-api.coolgame.world/login-api/v1/manager/auth/login";
        String json = "{\"account\": \"xiaoma\", \"password\": \"maxiaolei1234567\"}";
        if (JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data").get("token")!=null){
            return (String) JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data").get("token");
        }else{
            System.out.println("获取token失败："+JSONObject.parseObject(HttpConfigure.resp(HttpConfigure.postRequest(url, json))).getJSONObject("data"));
        }
        return null;
    }

}


