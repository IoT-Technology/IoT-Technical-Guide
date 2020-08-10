package iot.technology.custom.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import iot.technology.custom.codec.PacketCodecHandler;
import iot.technology.custom.codec.Spliter;
import iot.technology.custom.handler.IotIdleStateHandler;
import iot.technology.custom.server.handler.AuthHandler;
import iot.technology.custom.server.handler.CustomProtocolHandler;
import iot.technology.custom.server.handler.HeartBeatRequestHandler;
import iot.technology.custom.server.handler.LoginRequestHandler;

import java.util.Date;

/**
 * @author james mu
 * @date 2020/5/26 22:56
 */
public class CustomProtocolServer {

    private static final int PORT = 8000;

    public static void main(String[] args) throws Exception {
      NioEventLoopGroup bossGroup = new NioEventLoopGroup();
      NioEventLoopGroup workerGroup = new NioEventLoopGroup();

      final ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap
              .group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 1024)
              .childOption(ChannelOption.SO_KEEPALIVE, true)
              .childOption(ChannelOption.TCP_NODELAY, true)
              .childHandler(new ChannelInitializer<NioSocketChannel>() {
                  @Override
                  protected void initChannel(NioSocketChannel ch) throws Exception {
                      ch.pipeline().addLast(new IotIdleStateHandler());
                      ch.pipeline().addLast(new Spliter());
                      ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                      ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                      ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                      ch.pipeline().addLast(AuthHandler.INSTANCE);
                      ch.pipeline().addLast(CustomProtocolHandler.INSTANCE);
                  }
              });
      bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
