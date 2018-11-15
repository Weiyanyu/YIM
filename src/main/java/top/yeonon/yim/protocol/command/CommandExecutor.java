package top.yeonon.yim.protocol.command;


import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:50
 **/
public interface CommandExecutor {

    void exec(Scanner scanner, Channel channel);
}
