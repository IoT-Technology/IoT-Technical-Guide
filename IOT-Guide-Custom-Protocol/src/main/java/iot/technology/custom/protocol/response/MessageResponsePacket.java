package iot.technology.custom.protocol.response;

import iot.technology.custom.protocol.Packet;

import static iot.technology.custom.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author james mu
 * @date 2020/7/31 15:49
 */
public class MessageResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
