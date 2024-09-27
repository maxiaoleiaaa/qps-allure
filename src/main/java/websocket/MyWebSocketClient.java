package websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class MyWebSocketClient extends WebSocketClient {
    private  final  static int RUN_NUM = 10;
    private final static int[] BYTE_NUM = {1,1};
//构造器 只有构造器与类名同名
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("连接已建立");

        int[] data = BYTE_NUM;
        ByteBuffer allocate = ByteBuffer.allocate(data.length * 4);
        for (int value : data) {
            allocate.putInt(value);
        }
        byte[] binaryData = allocate.array();
        // 创建一个二进制请求示例
        // 示例二进制数据
        for (int i = 0; i < RUN_NUM; i++) {
            send(ByteBuffer.wrap(binaryData));
        }
    }

    @Override
    public void onMessage(String message) {
        System.out.println("收到文本消息: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("收到二进制消息: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("连接已关闭: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("错误: " + ex.getMessage());
    }

    public static void main(String[] args) {
        try {
            // 替换为你的 WebSocket URL
            URI uri = URI.create("wss://dev-platform-games-api.coolgame.world/slot/socket?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBZ2VudElkIjoieWVldSIsIkFjY291bnQiOiIyMjJPMEU0T0VCQiIsIkFjY291bnRJZCI6IjM5MDA4OSIsIlVzZXJJZCI6IjFhYWE5NmI3LTk2OTgtNGU0Mi05M2FhLTdhNDg2YzNhNTZlYyIsImV4cCI6MTcyOTMxNzE2M30.2rDcf4NTrSapqNdQKMa87kbcaB1woh5TWZq_s6lNOgs");
            MyWebSocketClient client = new MyWebSocketClient(uri);
            client.connect(); // 连接到服务器

            // 保持程序运行以接收消息
            while (!client.isOpen()) {
                Thread.sleep(100); // 等待连接建立
            }


            // 等待一段时间以接收消息
            Thread.sleep(60000);
            client.close(); // 关闭连接

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}