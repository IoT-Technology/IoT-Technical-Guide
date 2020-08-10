package iot.technology.custom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.response.LoginResponsePacket;
import iot.technology.custom.session.Session;
import iot.technology.custom.util.SessionUtil;


/**
 * @author james mu
 * @date 2020/8/10 17:24
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String clientId = loginResponsePacket.getClientId();

        if (loginResponsePacket.getSuccess()) {
            System.out.println("[" + clientId + "]登录成功, clientId 为: " + clientId);
            SessionUtil.bindSession(new Session(clientId), ctx.channel());
        } else {
            System.out.println("[" + clientId + "]登录失败,原因:" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }
}
