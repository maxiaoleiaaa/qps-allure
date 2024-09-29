package artob;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
public class Blogin {
    //  登录 并获取token
    public static String login() throws IOException {
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


