package iot.technology.mqtt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: 穆书伟
 * @Date: 21-3-9 下午20:07
 * @Version 2.0
 */
@Slf4j
@Service("iotMqttServer")
public class IOTMqttServer {

    @Value("${server.bind_port}")
    private Integer port;
    @Value("${server.netty.boss_group_thread_count}")
    private Integer bossGroupThreadCount;
    @Value("${server.netty.worker_group_thread_count}")
    private Integer workerGroupThreadCount;
    @Value("${server.netty.leak_detector_level}")
    private String leakDetectorLevel;
    @Value("${server.netty.max_payload_size}")
    private Integer maxPayloadSize;

    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    @PostConstruct
    public void init() throws Exception {
        log.info("Setting resource leak detector level to {}", leakDetectorLevel);
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(leakDetectorLevel.toUpperCase()));

        log.info("Starting Server");
        //创建boss线程组 用于服务端接受客户端的连接
        bossGroup = new NioEventLoopGroup(bossGroupThreadCount);
        // 创建 worker 线程组 用于进行 SocketChannel 的数据读写
        workerGroup = new NioEventLoopGroup(workerGroupThreadCount);
        // 创建 ServerBootstrap 对象
        ServerBootstrap b = new ServerBootstrap();
        //设置使用的EventLoopGroup
        b.group(bossGroup, workerGroup)
                //设置要被实例化的为 NioServerSocketChannel 类
                .channel(NioServerSocketChannel.class)
                // 设置 NioServerSocketChannel 的处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                // 设置连入服务端的 Client 的 SocketChannel 的处理器
                .childHandler(new MqttTransportServerInitializer(maxPayloadSize));
        // 绑定端口，并同步等待成功，即启动服务端
        channelFuture = b.bind(port).sync();

        log.info("Server started!");

    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        log.info("Stopping Server");
        try {
            // 监听服务端关闭，并阻塞等待
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭两个 EventLoopGroup 对象
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
        log.info("server stopped!");

    }
}
