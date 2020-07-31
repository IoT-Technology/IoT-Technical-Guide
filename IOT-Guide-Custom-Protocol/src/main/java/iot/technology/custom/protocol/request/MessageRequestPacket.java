package iot.technology.custom.protocol.request;

import iot.technology.custom.protocol.Packet;

import static iot.technology.custom.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author james mu
 * @date 2020/7/31 15:48
 */
public class MessageRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
