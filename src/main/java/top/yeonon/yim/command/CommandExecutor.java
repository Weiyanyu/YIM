package top.yeonon.yim.command;


import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 命令行执行器接口
 * @Author yeonon
 * @date 2018/11/15 0015 20:50
 **/
public interface CommandExecutor {

    /**
     * 执行命令
     * @param scanner 接受输入
     * @param channel 客户端channel
     */
    void exec(Scanner scanner, Channel channel);
}
