package artob.vip;

import artob.ConfigHttp;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

public class VipName {
    @DisplayName("实名认证列表")
    String nameList() throws IOException {
        String url = "https://dev-api.coolgame.world/admin-api/v1/assets_withdraw/withdraw/user/auth/list";
        String json = "{\"page\":1,\"page_size\":20}";
        return ConfigHttp.resp(ConfigHttp.postTokenAr(url,json));
    }
    @Test
    @DisplayName("修改第一条数据并查看修改结果是否正确")
    void upDate() throws IOException {
        if (nameList() !=null) {
            Long aLong = JSONObject.parseObject(nameList()).getJSONObject("data").getJSONArray("data").getJSONObject(0).getLong("id");
//        读取list列表的数据
            String name = "xiaoma";
            String auth = "411625";
//        修改
            String url = "https://dev-api.coolgame.world/admin-api/v1/assets_withdraw/withdraw/user/auth/update";
            String json = "{\"id\": " + aLong + ", \"updated_at\": 1726811873, \"created_at\": 1726811873, \"user_id\": 390011, \"user_name\": \"" + name + "\", \"user_auth\": \"" + auth + "\"}";
            ConfigHttp.postTokenAr(url, json);
//            匹配字段是否修改且正确
            JSONArray idtest = JSONObject.parseObject(nameList()).getJSONObject("data").getJSONArray("data");
            for (int i = 0; i < idtest.size(); i++) {
                JSONObject jsonObject = idtest.getJSONObject(i);

                if (jsonObject.getLong("id")==aLong){
                    String names = jsonObject.getString("user_name");
                    String auths = jsonObject.getString("user_auth");
                    if (names.equals(name) &&auths.equals(auth)){
                        System.out.println("修改成功，修改内容为："+"id："+aLong+"修改姓名为："+names+"修改身份证号码为："+auths);
                    }else {
                        System.out.println("未成功修改，修改结果与预期结果不一致");
                    }
                    System.out.println("未找到id："+aLong);
                }
            }
        }else {
            System.out.println("列表没有数据存在！");
        }
    }
}
