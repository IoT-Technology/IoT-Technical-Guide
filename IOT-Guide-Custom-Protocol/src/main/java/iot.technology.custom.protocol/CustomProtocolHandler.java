package iot.technology.custom.protocol;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author james mu
 * @date 2020/5/26 23:01
 */
@ChannelHandler.Sharable
public class CustomProtocolHandler extends SimpleChannelInboundHandler implements GenericFutureListener<Future<? super Void>> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void operationComplete(Future<? super Void> future) throws Exception {

    }
}
