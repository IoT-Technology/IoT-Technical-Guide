package iot.technology.custom.protocol.command;

/**
 * @author james mu
 * @date 2020/7/31 15:27
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;

    Byte HEARTBEAT_REQUEST = 7;

    Byte HEARTBEAT_RESPONSE = 8;
}
