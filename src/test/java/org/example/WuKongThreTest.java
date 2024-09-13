package org.example;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WuKongThreTest {

// ------------------命中率 92%
//    存储变量
    private static final String URL = "https://dev-platform-games-api.coolgame.world/slot/api/game/mock";
    private static final int BET = 100; //下注金额
    private static final int NUM_RUNS =300; //执行次数
    private static final int NUM_RTP =92; //中奖率

//发送接口请求
    private static HttpResponse sendPostRequest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL);
        String json = "{\"bet\": " + BET + ", \"rtp\": "+NUM_RTP+"}";
        post.setEntity(new org.apache.http.entity.StringEntity(json));
        post.setHeader("Content-Type", "application/json");
        CloseableHttpResponse execute = httpClient.execute(post);
        return execute;
    }
//    请求编码

    private static String reslut() throws IOException {
        HttpEntity entity = sendPostRequest().getEntity();
        String responseBody = EntityUtils.toString(entity,"utf-8");
        return responseBody;
    }
@Test
public void calculatePayout() throws IOException {
    double sumsamount = 0;//总中奖金额
    double sumfrequency = 0;//总执行次数
        long statimestamp  = System.currentTimeMillis();//开始计时
        for (int i = 0; i < NUM_RUNS; i++) {
            JSONObject jsonObject = JSONObject.parseObject(reslut());
            sumfrequency++;
            Integer k = (Integer) jsonObject.getJSONObject("data").get("payout");
            System.out.println("中奖金额"+k+"元");
            sumsamount +=k.doubleValue();//循环递增金额
        }
        long endtimestamp = System.currentTimeMillis();//结束计时
    System.out.println("开始时间"+time(statimestamp));
        System.out.println("累计执行"+sumfrequency+"次");
        System.out.println("累计中奖"+sumsamount+"元");
        //      根据接口请求次数 * 投注金额 = 总共有多少钱
        double totalamount = BET  * NUM_RUNS;//总投注金额
        System.out.println("总投注金额："+totalamount);
        double probability = sumsamount / totalamount;//总中奖金额/总投注金额
        System.out.println("总中奖金额/总投注金额=中奖率: " + probability+"%");
    System.out.println("结束时间"+time(statimestamp));
    }
//    记录时间方法
private static String time(long times){
    Date date = new Date();
    SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = sdf.format(date);
    return formattedDate;
}

}
