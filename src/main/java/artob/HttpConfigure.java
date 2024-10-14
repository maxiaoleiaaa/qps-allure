package artob;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpConfigure {
    /*发送 POST 请求的方法*/
    public static CloseableHttpResponse postRequest(String url, String json) throws IOException {
//   创建客户端
        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.setHeader("source", "pc");
            httpPost.setHeader("businesscode", "10000001");
            return aDefault.execute(httpPost);
        }
    }

    /*7.bet发送带token POST 请求的方法*/
//    public static CloseableHttpResponse postTokenBet(String url, String json) throws IOException {
//        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(new StringEntity(json));
//            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
//            httpPost.setHeader("source", "pc");
//            httpPost.setHeader("version", "0.0.1");
//            httpPost.setHeader("business", "7bet-admin");
//            httpPost.setHeader("authorizationjwt", Blogin.betLogin());
//            return aDefault.execute(httpPost);
//        }
//    }

    /*ar发送带token POST 请求的方法*/
//    public static CloseableHttpResponse postTokenAr(String url, String json) throws IOException {
//        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(new StringEntity(json,"utf-8"));
//            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
//            httpPost.setHeader("source", "pc");
//            httpPost.setHeader("version", "0.0.1");
//            httpPost.setHeader("business", "ar-admin");
//            httpPost.setHeader("authorizationjwt", Blogin.arLogin());
//            return aDefault.execute(httpPost);
//        }
//    }


    /*发送get请求*/
    public static CloseableHttpResponse getRequest(String url) throws IOException {
        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
            httpGet.setHeader("source", "pc");
            return aDefault.execute(httpGet);
        }
    }

    /*ar发送带token get请求*/
//    public static CloseableHttpResponse getTokenAr(String url) throws IOException {
//        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
//            httpGet.setHeader("source", "pc");
//            httpGet.setHeader("authorizationjwt", Blogin.arLogin());
//            httpGet.setHeader("version", "0.0.1");
//            httpGet.setHeader("business", "ar-admin");
//            return aDefault.execute(httpGet);
//        }
//    }

    /*7.bet发送带token get请求*/
//    public static CloseableHttpResponse getTokenBet(String url) throws IOException {
//        try (CloseableHttpClient aDefault = HttpClients.createDefault()) {
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
//            httpGet.setHeader("source", "pc");
//            httpGet.setHeader("authorizationjwt", Blogin.betLogin());
//            httpGet.setHeader("version", "0.0.1");
//            httpGet.setHeader("business", "7bet-admin");
//            return aDefault.execute(httpGet);
//        }
//    }

    /*接收请求并获取返回体*/
    public static String resp(CloseableHttpResponse response) throws IOException {
//        获取 CloseableHttpResponse 这个方法是否读取到了返回内容
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String string = EntityUtils.toString(entity,"utf-8");
            return string;
        } else
            return null;
    }

/* 创建线程池*/
public static ExecutorService exeSer(int thread){
    ExecutorService executorService = Executors.newFixedThreadPool(thread);
    return executorService;
}

/* 一次循环发出多次请求*/
    public static void forMax(){

    }
}

