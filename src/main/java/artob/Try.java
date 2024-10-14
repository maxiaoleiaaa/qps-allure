package artob;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Try {
    static int run = 10;
    static AtomicInteger requestCount = new AtomicInteger(0); // 统计请求次数
    static AtomicInteger successCount = new AtomicInteger(0); // 统计成功请求次数
    static AtomicInteger failureCount = new AtomicInteger(0); // 统计失败请求次数
    static AtomicLong totalResponseTime = new AtomicLong(0); // 统计总响应时间
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < run; i++) {
            List<Runnable> tasks = new ArrayList<>();
            for (int j = 0; j <1 ; j++) {
                tasks.add(() -> {
                    long startTime = System.currentTimeMillis();
                    try {
                        CloseableHttpResponse closeableHttpResponse = HttpConfigure.postRequest("https://saas-br-api.fb333api.com/login-api/v1/user/auth/login", par());
                        System.out.println(HttpConfigure.resp(closeableHttpResponse));
                        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(closeableHttpResponse.toString());
                        com.alibaba.fastjson.JSONObject code = jsonObject.getJSONObject("code");
                        System.out.println(code);
                        successCount.incrementAndGet(); // 增加成功计数
                    } catch (Exception e) {
                        failureCount.incrementAndGet(); // 增加失败计数
                        throw new RuntimeException(e);
                    }
                    finally {
                        long endTime = System.currentTimeMillis();
                        long responseTime = endTime - startTime;
                        totalResponseTime.addAndGet(responseTime);
                        requestCount.incrementAndGet(); // 增加请求计数
                    }
                });
                // 提交任务到线程池
                for (Runnable task : tasks) {
                    executorService.submit(task);
                }
            }

        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        // 输出压测报告
        printLoadTestReport();
    }
    public  static  void  printLoadTestReport(){
        int totalRequests = requestCount.get();
        int successfulRequests = successCount.get();
        int failedRequests = failureCount.get();
        double averageResponseTime = (double) totalResponseTime.get() / totalRequests;
        System.out.println("请求次数"+totalRequests+"成功请求次数"+successfulRequests+"失败次数"+failedRequests+"响应时间"+averageResponseTime);

    }

    public final static String par(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "xiaolei333");
        jsonObject.put("password", "xiaolei333");
        jsonObject.put("google_auth_code", "");
        // 转换为字符串
        String jsonString = jsonObject.toString();
        return jsonString;
    }

}
