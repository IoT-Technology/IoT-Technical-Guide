package iot.technology.gateway;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

/**
 * @author james mu
 * @date 2019/8/22 上午10:34
 */
public class GatewayTransportServerInitializer extends ChannelInitializer<SocketChannel> {

    private final int maxPayloadSize;

    public GatewayTransportServerInitializer(int maxPayloadSize){
        this.maxPayloadSize = maxPayloadSize;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new MqttDecoder(maxPayloadSize));
        pipeline.addLast("encoder", MqttEncoder.INSTANCE);
        GatewayTransportHandler handler = new GatewayTransportHandler();
        pipeline.addLast(handler);
        socketChannel.closeFuture().addListener(handler);

    }
}
