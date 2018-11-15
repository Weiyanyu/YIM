package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:20
 **/
public class LoginCommandExecutor implements CommandExecutor {


    public static final LoginCommandExecutor INSTANCE;

    static {
        INSTANCE = new LoginCommandExecutor();
    }

    private LoginCommandExecutor() {

    }



    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        String password = "pwd";
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket(username, password);
        channel.writeAndFlush(loginRequestPacket);
        waitFor();
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
