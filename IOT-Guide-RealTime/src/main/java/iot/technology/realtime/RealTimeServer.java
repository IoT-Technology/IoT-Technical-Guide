package iot.technology.realtime;


import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * @author james mu
 * @date 2020/5/20 17:08
 */
public class RealTimeServer {

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("ackevent1", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(final SocketIOClient client, ChatObject data, final AckRequest ackRequest) {

                /**
                 * 客户端请求检查ack, 但不需要检查
                 */
                if (ackRequest.isAckRequested()) {
                    /**
                     * 向客户端发送带数据的确认响应
                     */
                    ackRequest.sendAckData("客户端消息已传递到服务器!", "你说的东西挺有意思的!");
                }

                /**
                 * 使用带数据的ack回调将消息返回客户端
                 */
                ChatObject ackChatObjectData = new ChatObject(data.getUserName(), "包含ack数据的消息");
                client.sendEvent("ackevent2", new AckCallback<String>(String.class) {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("ack from client: " + client.getSessionId() + " data: " + result);
                    }
                }, ackChatObjectData);

                ChatObject ackChatObjectData1 = new ChatObject(data.getUserName(), "不带有ack数据的消息");
                client.sendEvent("ackevent3", new VoidAckCallback() {

                    @Override
                    protected void onSuccess() {
                        System.out.println("void ack from: " + client.getSessionId());
                    }

                }, ackChatObjectData1);
            }
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}
