package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:27
 **/
public class JoinGroupCommandExecutor implements CommandExecutor {

    public static final JoinGroupCommandExecutor INSTANCE;

    static {
        INSTANCE = new JoinGroupCommandExecutor();
    }

    private JoinGroupCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【加入群组】输入 groupId：");
        long groupId = scanner.nextLong();

        //构造请求对象
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
