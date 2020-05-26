package iot.technology.custom.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;

/**
 * @author james mu
 * @date 2020/5/26 22:56
 */
public class CustomProtocolServer {

    private static final int PORT = 4567;
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
                    .childHandler(new CustomProtocolInitializer(maxPayloadSize));
            ChannelFuture f = b.bind(PORT);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
