package iot.technology.custom.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author james mu
 * @date 2020/8/2 21:07
 */
@Slf4j
public class IotIdleStateHandler extends IdleStateHandler {

    public static final int READER_IDLE_TIME = 15;

    public IotIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        log.info("{}: 秒内未读到数据，关闭连接", READER_IDLE_TIME);
        ctx.channel().close();
    }
}
