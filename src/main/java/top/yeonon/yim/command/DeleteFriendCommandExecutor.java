package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendRequestAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 17:47
 **/
public class DeleteFriendCommandExecutor implements CommandExecutor {

    public static final DeleteFriendCommandExecutor INSTANCE = new DeleteFriendCommandExecutor();

    private DeleteFriendCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要删除的好友ID：");
        long toUserId = scanner.nextLong();

        if (toUserId == SessionUtil.getSession(channel).getUserId()) {
            System.out.println("不要删除自己！");
            return;
        }

        DeleteFriendRequestAbstractPacket requestPacket = new DeleteFriendRequestAbstractPacket();
        requestPacket.setUserId(toUserId);
        channel.writeAndFlush(requestPacket);
    }
}
