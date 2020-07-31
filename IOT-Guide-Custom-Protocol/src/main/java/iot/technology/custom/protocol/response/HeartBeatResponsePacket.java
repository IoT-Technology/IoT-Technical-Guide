package iot.technology.custom.protocol.response;

import iot.technology.custom.protocol.Packet;

import static iot.technology.custom.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @author james mu
 * @date 2020/7/31 15:47
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
