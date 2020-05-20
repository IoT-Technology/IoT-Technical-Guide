package iot.technology.mqtt;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

/**
 * @Author: 穆书伟
 * @Date: 19-4-3 下午3:26
 * @Version 1.0
 */
public class MqttTransportServerInitializer  extends ChannelInitializer<SocketChannel> {

    private final int maxPayloadSize;

    public MqttTransportServerInitializer(int maxPayloadSize){
        this.maxPayloadSize = maxPayloadSize;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new MqttDecoder(maxPayloadSize));
        pipeline.addLast("encoder", MqttEncoder.INSTANCE);
        MqttTransportHandler handler = new MqttTransportHandler();
        pipeline.addLast(handler);
        socketChannel.closeFuture().addListener(handler);

    }
}
