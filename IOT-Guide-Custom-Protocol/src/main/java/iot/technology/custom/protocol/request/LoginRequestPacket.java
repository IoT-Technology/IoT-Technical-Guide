package iot.technology.custom.protocol.request;

import iot.technology.custom.protocol.Packet;
import lombok.Data;

import static iot.technology.custom.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author james mu
 * @date 2020/7/31 15:29
 */
@Data
public class LoginRequestPacket extends Packet {

    private String clientId;

    private String password;

    @Override
    public byte getCommand() {
        return LOGIN_REQUEST;
    }
}
