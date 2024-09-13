package org.example;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Description;
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

public class WuKongTwoTest {


    // ------------------命中率 88%
//    存储变量
    private static final String URL = "https://dev-platform-games-api.coolgame.world/slot/api/game/mock";
    private static final int BET = 100; //下注金额
    private static final int NUM_RUNS =100; //执行次数
    private static final int NUM_RTP =95; //命中率

//发送接口请求
    private static HttpResponse sendPostRequest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL);
        String json = "{\"bet\": " + BET + ", \"rtp\": "+ NUM_RTP +"}";
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
    @Description("命中率95")
    public void calculatePayout() throws IOException {
        double sumsamount = 0;//总中奖金额
        double sumfrequency = 0;//总执行次数
        double sumwinning =0;//累计中奖次数
        long statimestamp  = System.currentTimeMillis();//开始计时
        for (int i = 0; i < NUM_RUNS; i++) {
            JSONObject jsonObject = JSONObject.parseObject(reslut());
            sumfrequency++;
            Integer k = (Integer) jsonObject.getJSONObject("data").get("payout");
            if (k >0){
                sumwinning++;
            }
            System.out.println("中奖金额"+k+"元");
            sumsamount +=k.doubleValue();//循环递增金额
        }
        long endtimestamp = System.currentTimeMillis();//结束计时
        double totalamount = BET  * NUM_RUNS;//总投注金额
        double zhongjiang = sumwinning/sumfrequency;//中奖次数 / 投注次数
        double probability = sumsamount / totalamount;//总中奖金额 / 总投注金额

        System.out.println("**********开始时间："+time(statimestamp)+
                "\n"+ "执行次数："+sumfrequency+"次"+
                "\n"+"累计中奖："+sumsamount+"元" +
                "\n" +"总投注金额："+totalamount+"元"+
                "\n"+"累计中奖率为："+zhongjiang+"%" +
                "\n" + "总中奖金额/总投注金额=中奖率: " + probability+"%" +
                "\n" + "结束时间："+time(statimestamp)+"***********");
    }
//    记录时间方法
private static String time(long times){
    Date date = new Date();
    SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = sdf.format(date);
    return formattedDate;
}

}
