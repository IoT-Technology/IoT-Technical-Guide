package iot.technology.custom.protocol.response;

import iot.technology.custom.protocol.Packet;

import static iot.technology.custom.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @author james mu
 * @date 2020/7/31 15:30
 */
public class LogoutResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
