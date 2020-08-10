package iot.technology.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import iot.technology.custom.protocol.Packet;
import iot.technology.custom.protocol.PacketCodec;


/**
 * @author james mu
 * @date 2020/8/10 14:36
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, packet);
    }
}
