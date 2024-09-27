import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//继承 CaseApi接口
//此类用于接口入参
public class Case implements CaseApi {

    // 发送接口请求
    private static CloseableHttpResponse sendPostRequest(int bet, int rtp) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://dev-platform-games-api.coolgame.world/slot/api/game/mock");
        String json = "{\"bet\": " + bet + ", \"rtp\": " + rtp + "}";
        post.setEntity(new org.apache.http.entity.StringEntity(json,"utf-8"));
        post.setHeader("Content-Type", "application/json");
        CloseableHttpResponse execute = httpClient.execute(post);
        return execute;
    }

    // 请求编码
    private static String result(int bet, int rtp) throws IOException {
        HttpEntity entity = sendPostRequest(bet, rtp).getEntity();
        return EntityUtils.toString(entity, "utf-8");
    }

    public  Result calculatePayout(int bet, int rtp, int runs) throws IOException {
        double sumsamount = 0;//总中奖金额
        double sumfrequency = 0;//总执行次数
        double sumwinning = 0;//累计中奖次数
        long statimestamp = System.currentTimeMillis();//开始计时
        for (int i = 0; i < runs; i++) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(result(bet, rtp));
                sumfrequency++;
                Integer k = (Integer) jsonObject.getJSONObject("data").get("payout");
                if (k > 0) {
                    sumwinning++;
                }
                sumsamount += k.doubleValue();//循环递增金额
            }catch (Exception e){
                System.out.println("报错信息："+e.getMessage());
            }
        }
        long endtimestamp = System.currentTimeMillis();//结束计时
        double totalamount = bet * runs;//总投注金额
        double zhongjiang = sumwinning / sumfrequency * 100;//中奖次数 / 投注次数 = 中奖率
        double probability = sumsamount / totalamount;//总中奖金额 / 总投注金额 = 收益率
        return new Result(
                statimestamp,
                sumsamount,
                totalamount,
                zhongjiang,
                probability,
                endtimestamp);
    }
    private static String time(long data){
        Date date = new Date(data);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String setdata = simpleDateFormat.format(date);
        return setdata;
    }

    //反参
    class Result {
        private final long statimestamp;
        private final double totalamount;//总投注金额
        private final double zhongjiang;//中奖率
        private final double probability;//收益率
        private final double sumsamount;
        private final long endtimestamp;


        private Result(long statimestamp, double sumsamount, double totalamount, double zhongjiang,  double probability, long endtimestamp) {
            this.statimestamp = statimestamp;
            this.totalamount = totalamount;
            this.zhongjiang = zhongjiang;
            this.probability = probability;
            this.sumsamount = sumsamount;
            this.endtimestamp = endtimestamp;
        }


        // 实例化反参
        @Override
        public String toString() {
            return "**********开始时间：" + time(statimestamp) +
                    "\n" + "累计中奖：" + sumsamount + "元" +
                    "\n" + "总投注金额：" + totalamount + "元" +
                    "\n" + "累计中奖率为：" + zhongjiang + "%" +
                    "\n" + "总中奖金额/总投注金额=中奖率: " + probability + "%" +
                    "\n" + "结束时间：" + time(endtimestamp) + "***********";
        }
    }
}
