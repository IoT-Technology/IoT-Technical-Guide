package iot.technology.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import iot.technology.custom.protocol.PacketCodec;

/**
 * @author james mu
 * @date 2020/8/10 14:13
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 5;
    private static final int LENGTH_FIELD_LENGTH = 2;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
       if (in.getShort(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
           ctx.channel().close();
           return null;
       }

       return super.decode(ctx, in);
    }
}
