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
 * +---------+---------+---------+----------+-----------+----------------+
 * |         |         |         |          |           |                |
 * |   2     |   1     |    1    |     1    |     2     |       N        |
 * |         |         |         |          |           |                |
 * +----------------------------------------------------------------------
 * |         |         |         |          |           |                |
 * |   STR   |   CMD   |  SWV    |   ENM    |   LEN     |      DAT       |
 * |         |         |         |          |           |                |
 * +---------+---------+---------+----------+-----------+----------------+
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

    public static final short MAGIC_NUMBER = 0x2323;
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
        // 1. 序列化 java对象
        byte[] bytes = Encryption.DEFAULT.encrypt(packet);

        // 2. 实际编码过程
        byteBuf.writeShort(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(packet.getSwv());
        byteBuf.writeByte(Encryption.DEFAULT.getEncryptionAlgorithm());
        byteBuf.writeShort(bytes.length);
        byteBuf.writeBytes(bytes);

    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(2);

        // 指令
        byte command = byteBuf.readByte();

        // 版本号
        byte swv = byteBuf.readByte();

        // 数据加密方式
        byte enm = byteBuf.readByte();

        // 数据包长度
        short length = byteBuf.readShort();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

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
