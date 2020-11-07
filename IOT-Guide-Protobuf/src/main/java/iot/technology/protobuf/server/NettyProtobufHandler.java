package iot.technology.protobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import iot.technology.protobuf.proto.UserMsg;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NettyProtobufHandler extends ChannelInboundHandlerAdapter {

    /** 空闲次数 */
    private AtomicInteger idle_count = new AtomicInteger(1);
    /** 发送次数 */
    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址:" + ctx.channel().remoteAddress());
        log.info("连接的客户端地址:" + ctx.channel().remoteAddress());
        UserMsg.User user = UserMsg.User.newBuilder()
                .setId(1)
                .setAge(24)
                .setName("iot")
                .setState(0)
                .build();
        ctx.writeAndFlush(user);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("第" + count.get() + "次" + "，服务端接受的消息:" + msg);
        try {
            // 如果是protobuf类型的数据
            if (msg instanceof UserMsg.User) {
                UserMsg.User user = (UserMsg.User) msg;
                if (user.getState() == 1) {
                    log.info("客户端业务处理成功!");
                } else if(user.getState() == 2){
                    log.info("接受到客户端发送的心跳!");
                }else{
                    log.info("未知命令!");
                }
            } else {
                log.info("未知数据!" + msg);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
        count.getAndIncrement();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                log.info("已经5秒没有接收到客户端的信息了");
                if (idle_count.get() > 1) {
                    log.info("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
                idle_count.getAndIncrement();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
