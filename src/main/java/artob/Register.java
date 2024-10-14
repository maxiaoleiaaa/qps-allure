package artob;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Register {
    static int num = 0;
    static int run = 5000;
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < run; i++) {
            List<Runnable> tasks = new ArrayList<>();
            for (int j = 0; j <20 ; j++) {
                tasks.add(()->{
                    try {
                        CloseableHttpResponse closeableHttpResponse = HttpConfigure.postRequest("https://saas-br-api.fb333api.com/login-api/v1/user/auth/register", par());
                        num  ++;
                        System.out.println(HttpConfigure.resp(closeableHttpResponse));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                // 提交任务到线程池
                for (Runnable task : tasks) {
                    executorService.submit(task);
                }
            }
        }

    }
    public final static String par(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "xiaoma"+num);
        jsonObject.put("password", "case321");
        jsonObject.put("invite_code", "");
        jsonObject.put("phone_code", "000555");
        jsonObject.put("verification_code", "");
        // 转换为字符串
        String jsonString = jsonObject.toString();
        return jsonString;
    }




}


