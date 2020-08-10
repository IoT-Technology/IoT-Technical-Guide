package iot.technology.custom.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

import static iot.technology.custom.protocol.command.Command.LOGOUT_REQUEST;
import static iot.technology.custom.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author james mu
 * @date 2020/8/10 15:00
 */
@ChannelHandler.Sharable
public class CustomProtocolHandler extends SimpleChannelInboundHandler<Packet> {
    public static final CustomProtocolHandler INSTANCE = new CustomProtocolHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private CustomProtocolHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
