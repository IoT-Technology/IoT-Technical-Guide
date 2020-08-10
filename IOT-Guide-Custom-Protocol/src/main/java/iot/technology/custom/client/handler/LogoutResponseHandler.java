package iot.technology.custom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.response.LogoutResponsePacket;
import iot.technology.custom.util.SessionUtil;

/**
 * @author james mu
 * @date 2020/8/10 17:32
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
