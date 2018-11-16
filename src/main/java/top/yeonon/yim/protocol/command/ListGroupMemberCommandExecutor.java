package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberRequestPacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 13:46
 **/
public class ListGroupMemberCommandExecutor implements CommandExecutor {

    public static final ListGroupMemberCommandExecutor INSTANCE;

    static {
        INSTANCE = new ListGroupMemberCommandExecutor();
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，获取群成员列表：");
        long groupId = scanner.nextLong();

        //构造请求对象
        ListGroupMemberRequestPacket listGroupMemberRequestPacket = new ListGroupMemberRequestPacket();
        listGroupMemberRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupMemberRequestPacket);
    }
}
