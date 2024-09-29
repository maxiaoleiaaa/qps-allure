package artob;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;

public class Vip {

    public static CloseableHttpResponse lockIp() throws IOException {
        final String url = "https://dev-api.coolgame.world/admin-api/v1/user/loginLogsList";
        String json = "{\"page\": 1, \"page_size\": 20, \"start_time\": 0, \"end_time\": 0}";
        return HttpConfigure.postToken(url,json);
    }

    public static CloseableHttpResponse lockUser() throws IOException {
        final String url = "https://dev-api.coolgame.world/admin-api/v1/loginlock/getLocking";
        String json = "{\"page\": 1, \"page_size\": 20, \"start_time\": 0, \"end_time\": 0}";
        return HttpConfigure.postToken(url,json);
    }

    public static CloseableHttpResponse vipList() throws IOException {
      final String url = "https://dev-api.coolgame.world/admin-api/v1/assets_withdraw/withdraw/user/auth/list";
      String json = "{\"page\": 1, \"page_size\": 20}";
      return HttpConfigure.postToken(url,json);
    }
    @Test
    public void test() throws IOException {
        System.out.println("已锁ip："+HttpConfigure.resp(lockIp())+
                "\n"+"用户实名列表"+HttpConfigure.resp(vipList()));
    }
}
