package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestPacket;
import top.yeonon.yim.server.handler.LoginRequestHandler;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:53
 **/
public class LogoutCommandExecutor implements CommandExecutor {

    public static final LogoutCommandExecutor INSTANCE;

    static {
        INSTANCE = new LogoutCommandExecutor();
    }

    private LogoutCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (!SessionUtil.hasLogin(channel)) {
            System.out.println("用户没有登录，不要尝试登出！");
            return;
        }
        Session session = SessionUtil.getSession(channel);
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        logoutRequestPacket.setUserId(session.getUserId());
        logoutRequestPacket.setUsername(session.getUsername());

        channel.writeAndFlush(logoutRequestPacket);

        //为了退出循环，设置中断标志
        Thread.currentThread().interrupt();

    }
}
