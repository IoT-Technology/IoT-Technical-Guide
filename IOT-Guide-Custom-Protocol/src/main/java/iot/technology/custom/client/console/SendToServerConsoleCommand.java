package iot.technology.custom.client.console;

import io.netty.channel.Channel;
import iot.technology.custom.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author james mu
 * @date 2020/8/10 17:41
 */
public class SendToServerConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给服务端: ");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(message, false, null));
    }
}
