package iot.technology.custom.protocol.request;

import iot.technology.custom.protocol.Packet;

import static iot.technology.custom.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author james mu
 * @date 2020/7/31 15:29
 */
public class LoginRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return LOGIN_REQUEST;
    }
}
