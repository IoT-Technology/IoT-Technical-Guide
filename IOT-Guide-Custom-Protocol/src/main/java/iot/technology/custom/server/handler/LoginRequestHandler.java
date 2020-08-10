package iot.technology.custom.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import iot.technology.custom.protocol.request.LoginRequestPacket;
import iot.technology.custom.protocol.response.LoginResponsePacket;
import iot.technology.custom.session.Session;
import iot.technology.custom.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author james mu
 * @date 2020/8/10 15:42
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setSwv(loginRequestPacket.getSwv());
        loginResponsePacket.setClientId(loginRequestPacket.getClientId());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String clientId = loginRequestPacket.getClientId();
            loginResponsePacket.setClientId(clientId);
            System.out.println("[" + loginRequestPacket.getClientId() + "]登录成功");
            SessionUtil.bindSession(new Session(clientId), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        //登录响应
        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
