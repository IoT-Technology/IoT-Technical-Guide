package iot.technology.custom.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author james mu
 * @date 2020/8/10 17:33
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
