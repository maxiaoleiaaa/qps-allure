package artob;



import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;


public class Blogin {
    private final static String URL = "https://dev-api.coolgame.world/login-api/v1/manager/auth/login";
//  登录
    public static void login() throws IOException {
        CloseableHttpClient http = HttpConfigure.http();
        HttpPost posts = HttpConfigure.post(URL);
        HttpConfigure.addParameter("account","xiaoma");
        HttpConfigure.addParameter("password","maxiaolei1234567");
        HttpConfigure.addHeader("Content-Type","application/json");
        posts.setEntity(new StringEntity(HttpConfigure.getParametersAsJson()));
    }
}
