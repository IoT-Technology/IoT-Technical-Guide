package iot.technology.custom.protocol;

import io.netty.buffer.ByteBuf;
import iot.technology.custom.encryption.Encryption;
import iot.technology.custom.encryption.impl.NotEncryption;
import iot.technology.custom.protocol.request.HeartBeatRequestPacket;
import iot.technology.custom.protocol.request.LoginRequestPacket;
import iot.technology.custom.protocol.request.LogoutRequestPacket;
import iot.technology.custom.protocol.request.MessageRequestPacket;
import iot.technology.custom.protocol.response.HeartBeatResponsePacket;
import iot.technology.custom.protocol.response.LoginResponsePacket;
import iot.technology.custom.protocol.response.LogoutResponsePacket;
import iot.technology.custom.protocol.response.MessageResponsePacket;

import java.util.HashMap;
import java.util.Map;

import static iot.technology.custom.protocol.command.Command.*;

/**
 *
 *<pre>
 * **********************************************************************************
 *                                   protocol
 * +---------+---------+---------+----------+-----------+----------------+-----------+
 * |         |         |         |          |           |                |           |
 * |   2     |   1     |    1    |     1    |     2     |       N        |     1     |
 * |         |         |         |          |           |                |           |
 * +---------------------------------------------------------------------------------+
 * |         |         |         |          |           |                |           |
 * |   STR   |   CMD   |  SWV    |   ENM    |   LEN     |      DAT       |    VER    |
 * |         |         |         |          |           |                |           |
 * +---------+---------+---------+----------+-----------+----------------+-----------+
 *
 * 消息头7个字节定长
 * = 2 // 起始符,STR: ASCII码 "##", 用0x23,0x23表示
 * + 1 // 命令单元
 * + 1 // 软件版本号,企业自定义的车载终端固件版本号
 * + 1 // 数据加密方式 0x01-不加密 0x02-对称AES128算法
 * + 2 // 数据部分的长度,short 类型
 * </pre>
 * @author james mu
 * @date 2020/7/27 13:45
 */
public class PacketCodec {

    public static final byte MAGIC_NUMBER1 = (byte) 0x23;
    public static final byte MAGIC_NUMBER2 = (byte) 0x23;
    public static final byte VER = (byte) 0x01;

    public static final PacketCodec INSTANCE = new PacketCodec();
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Encryption> serializerMap;

    private PacketCodec() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Encryption encryption = new NotEncryption();
        serializerMap.put(encryption.getEncryptionAlgorithm(), encryption);

    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Encryption.DEFAULT.encrypt(packet);

        byteBuf.writeByte(MAGIC_NUMBER1);
        byteBuf.writeByte(MAGIC_NUMBER2);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(packet.getSwv());
        byteBuf.writeByte(Encryption.DEFAULT.getEncryptionAlgorithm());
        byteBuf.writeShort(bytes.length);
        byteBuf.writeBytes(bytes);
        byteBuf.writeByte(VER);

    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(2);
        byte command = byteBuf.readByte();

        byte swv = byteBuf.readByte();
        byte enm = byteBuf.readByte();
        short length = byteBuf.readShort();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        byte ver = byteBuf.readByte();

        Class<? extends Packet> requestType = getRequestType(command);
        Encryption encryption = getEncryption(enm);

        if (requestType != null && encryption != null) {
            return encryption.decrypt(requestType, bytes);
        }
        return null;

    }

    private Encryption getEncryption(byte encryptionAlgorithm) {
        return serializerMap.get(encryptionAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
