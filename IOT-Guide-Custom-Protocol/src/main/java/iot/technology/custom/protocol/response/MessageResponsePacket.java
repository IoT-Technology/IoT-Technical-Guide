package iot.technology.custom.protocol.response;

import iot.technology.custom.protocol.Packet;
import lombok.Data;

import static iot.technology.custom.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author james mu
 * @date 2020/7/31 15:49
 */
@Data
public class MessageResponsePacket extends Packet {
    private String fromClientId;
    private String message;

    @Override
    public byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
