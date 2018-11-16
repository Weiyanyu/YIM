package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageRequestPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponsePacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:32
 **/
public class GroupMessageCommandExecutor implements CommandExecutor {

    public static final GroupMessageCommandExecutor INSTANCE;

    static {
        INSTANCE = new GroupMessageCommandExecutor();
    }

    private GroupMessageCommandExecutor() {}

    @Override
    public void exec(Scanner scanner, Channel channel) {
        channel.attr(Attributes.FINISHED_TASK).set(false);
        long toGroupId = scanner.nextLong();
        String message = scanner.next();

        //构造请求对象
        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        requestPacket.setToGroupId(toGroupId);
        requestPacket.setMessage(message);

        channel.writeAndFlush(requestPacket);
    }
}
