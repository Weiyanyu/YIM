package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;

/**
 * 登出命令执行器
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
        //构造一个请求对象
        LogoutRequestAbstractPacket logoutRequestPacket = new LogoutRequestAbstractPacket();
        logoutRequestPacket.setUserId(session.getUserId());
        logoutRequestPacket.setUsername(session.getUsername());

        channel.writeAndFlush(logoutRequestPacket);

        //为了退出循环，设置中断标志
        Thread.currentThread().interrupt();

    }
}
