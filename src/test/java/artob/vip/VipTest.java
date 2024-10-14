package artob.vip;

import artob.ConfigHttp;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
@DisplayName("会员层级")
class VipTest {
    @DisplayName("会员列表")
    public String vipList() throws IOException {
        final String url = "https://dev-api.coolgame.world/admin-api/v1/user/level/search";
        String json = "{\"agent_level\": -1, \"level\": -1, \"register_time\": [], \"page\": 1, \"page_size\": 20}";
       return ConfigHttp.resp(ConfigHttp.postTokenAr(url, json));
    }
}