package iot.technology.mqtt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;

/**
 * @Author: 穆书伟
 * @Date: 19-4-3 下午3:14
 * @Version 1.0
 */
public  class IOTMqttServer {

    private static final int PORT = 1883;
    private static final String leakDetectorLevel = "DISABLED";
    private static final Integer bossGroupThreadCount = 1;
    private static final Integer workerGroupThreadCount = 12;
    private static final Integer maxPayloadSize = 65536;

    public static void main(String[] args) throws Exception {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(leakDetectorLevel.toUpperCase()));

        EventLoopGroup bossGroup = new NioEventLoopGroup(bossGroupThreadCount);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerGroupThreadCount);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MqttTransportServerInitializer(maxPayloadSize));
            ChannelFuture f = b.bind(PORT);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
