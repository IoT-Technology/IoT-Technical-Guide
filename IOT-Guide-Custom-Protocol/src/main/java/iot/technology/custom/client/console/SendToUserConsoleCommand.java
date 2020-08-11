package iot.technology.custom.client.console;

import io.netty.channel.Channel;
import iot.technology.custom.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author james mu
 * @date 2020/8/10 17:52
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个设备: ");
        String toClientId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(message, true, toClientId));
    }
}
