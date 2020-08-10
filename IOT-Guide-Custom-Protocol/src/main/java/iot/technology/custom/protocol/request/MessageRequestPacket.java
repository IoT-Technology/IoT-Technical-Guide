package iot.technology.custom.protocol.request;

import iot.technology.custom.protocol.Packet;
import lombok.Data;

import static iot.technology.custom.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author james mu
 * @date 2020/7/31 15:48
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;
    private Boolean coordination;
    private String toClientId;

    public MessageRequestPacket(String message, boolean coordination, String toClientId) {
        this.message = message;
        this.coordination = coordination;
        this.toClientId = toClientId;
    }

    @Override
    public byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
