package artob;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;

public class LoginTest {
    public Object getToken() throws IOException {
        String url = "https://dev-api.coolgame.pro/login-api/v1/manager/auth/login";
        String json = "{\"account\": \"test012\", \"password\": \"aa123456\"}";
        if (JSONObject.parseObject(ConfigHttp.resp(ConfigHttp.postRequest(url, json))).getJSONObject("data").get("token")!=null){
            return  JSONObject.parseObject(ConfigHttp.resp(ConfigHttp.postRequest(url, json))).getJSONObject("data").get("token");
        }else{
            System.out.println("获取token失败："+JSONObject.parseObject(ConfigHttp.resp(ConfigHttp.postRequest(url, json))).getJSONObject("data"));
        }
        return null;
    }
}
