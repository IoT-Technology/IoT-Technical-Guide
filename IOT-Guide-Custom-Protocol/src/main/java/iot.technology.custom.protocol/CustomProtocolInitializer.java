package iot.technology.custom.protocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author james mu
 * @date 2020/5/26 23:12
 */
public class CustomProtocolInitializer extends ChannelInitializer<SocketChannel>{

    private final int maxPayloadSize;


    public CustomProtocolInitializer(int maxPayloadSize) {
        this.maxPayloadSize = maxPayloadSize;
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        CustomProtocolHandler handler = new CustomProtocolHandler();
        pipeline.addLast(handler);
        socketChannel.closeFuture().addListener(handler);
    }
}
