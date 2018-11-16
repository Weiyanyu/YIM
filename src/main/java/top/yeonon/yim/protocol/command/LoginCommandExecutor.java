package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.AttributeKey;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;

import java.util.Scanner;

/**
 * 登录命令执行器
 *
 * @Author yeonon
 * @date 2018/11/15 0015 21:20
 **/
public class LoginCommandExecutor implements CommandExecutor {


    //单例
    public static final LoginCommandExecutor INSTANCE;

    static {
        INSTANCE = new LoginCommandExecutor();
    }

    private LoginCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        channel.attr(Attributes.FINISHED_TASK).set(false);

        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        String password = "pwd";
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket(username, password);

        channel.writeAndFlush(loginRequestPacket);

        //waitFor();
    }

    //TODO 后面重构的时候将其做成异步的
    private static void waitFor() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
