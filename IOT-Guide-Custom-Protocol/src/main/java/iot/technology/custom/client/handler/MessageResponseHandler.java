package iot.technology.custom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.response.MessageResponsePacket;
import lombok.extern.slf4j.Slf4j;

/**
 * @author james mu
 * @date 2020/8/10 17:21
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromClientId = messageResponsePacket.getFromClientId();
        System.out.println(fromClientId + " -> " + messageResponsePacket.getMessage());
    }
}
