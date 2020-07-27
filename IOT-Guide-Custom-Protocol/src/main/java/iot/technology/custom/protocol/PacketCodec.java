package iot.technology.custom.protocol;

import io.netty.buffer.ByteBuf;
import iot.technology.custom.encryption.Encryption;
import iot.technology.custom.encryption.impl.NotEncryption;

import java.util.HashMap;
import java.util.Map;

/**
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
        serializerMap = new HashMap<>();
        Encryption encryption = new NotEncryption();
        serializerMap.put(encryption.getEncryptionAlgorithm(), encryption);

    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Encryption.DEFAULT.encrypt(packet);

        byteBuf.writeByte(MAGIC_NUMBER1);
        byteBuf.writeByte(MAGIC_NUMBER2);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeBytes(packet.getVin());
        byteBuf.writeByte(packet.getSwv());
        byteBuf.writeByte(Encryption.DEFAULT.getEncryptionAlgorithm());
        byteBuf.writeShort(bytes.length);
        byteBuf.writeBytes(bytes);
        byteBuf.writeByte(VER);

    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(2);
        byte command = byteBuf.readByte();

        byte[] vinByte = new byte[17];
        byteBuf.readBytes(vinByte);

        byte swv = byteBuf.readByte();
        byte enm = byteBuf.readByte();
        short length = byteBuf.readShort();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        byte ver = byteBuf.readByte();

        Class<? extends Packet> requestType = getRequestType(command);
        Encryption encryption = getEncryption(enm);

        if (requestType != null && encryption != null) {
            Packet packet = encryption.decrypt(requestType, bytes);
            packet.setVin(vinByte);
            return packet;
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
