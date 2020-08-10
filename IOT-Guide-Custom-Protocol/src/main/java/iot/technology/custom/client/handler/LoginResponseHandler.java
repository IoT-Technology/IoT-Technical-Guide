package iot.technology.custom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.response.LoginResponsePacket;
import iot.technology.custom.session.Session;
import iot.technology.custom.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author james mu
 * @date 2020/8/10 17:24
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String clientId = loginResponsePacket.getClientId();

        if (loginResponsePacket.getSuccess()) {
            log.info("[" + clientId + "]登录成功, clientId 为: " + clientId);
            SessionUtil.bindSession(new Session(clientId), ctx.channel());
        } else {
            log.error("[" + clientId + "]登录失败,原因:" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接被关闭!");
    }
}
