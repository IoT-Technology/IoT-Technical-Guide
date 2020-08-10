package iot.technology.custom.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.request.MessageRequestPacket;
import iot.technology.custom.protocol.response.MessageResponsePacket;
import iot.technology.custom.session.Session;
import iot.technology.custom.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author james mu
 * @date 2020/8/10 16:16
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        long begin = System.currentTimeMillis();

        //1. 拿到消息发送方的会话消息
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromClientId(messageRequestPacket.getToClientId());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        if (messageRequestPacket.getCoordination()) {
            Channel toClientChannel = SessionUtil.getChannel(messageRequestPacket.getToClientId());
            if (toClientChannel != null && SessionUtil.hasLogin(toClientChannel)) {
                toClientChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
                    if (future.isDone()) {

                    }
                });
            } else {
                System.out.println("[" + session.getClientId() + "] 不在线，发送失败!");
            }
        } else {
            System.out.println(session.getClientId() + ":" + messageRequestPacket.getMessage());
        }
    }
}
