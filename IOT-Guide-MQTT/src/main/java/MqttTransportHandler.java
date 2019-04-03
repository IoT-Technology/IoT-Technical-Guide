import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author: 穆书伟
 * @Date: 19-4-3 下午3:35
 * @Version 1.0
 */
public class MqttTransportHandler extends ChannelInboundHandlerAdapter implements GenericFutureListener<Future<? super Void>> {
    @Override
    public void operationComplete(Future<? super Void> future) throws Exception {

    }
}
