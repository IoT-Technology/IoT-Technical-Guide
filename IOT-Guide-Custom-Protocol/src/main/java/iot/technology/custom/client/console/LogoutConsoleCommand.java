package iot.technology.custom.client.console;

import io.netty.channel.Channel;
import iot.technology.custom.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * @author james mu
 * @date 2020/8/10 17:40
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
