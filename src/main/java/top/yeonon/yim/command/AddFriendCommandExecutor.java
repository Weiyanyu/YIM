package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 14:05
 **/
public class AddFriendCommandExecutor implements CommandExecutor {

    public static final AddFriendCommandExecutor INSTANCE = new AddFriendCommandExecutor();

    private AddFriendCommandExecutor() {}

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入对方id： ");
        long toUserId = scanner.nextLong();
        System.out.print("请输入请求消息： ");
        String requestMessage = scanner.next();

        long fromUserId = SessionUtil.getSession(channel).getUserId();
        String fromUsername = SessionUtil.getSession(channel).getUsername();
        //如果两者id相等，说明有问题，阻止本次操作
        if (fromUserId == toUserId) {
            System.out.println("不需要添加自己为好友。。。");
            return;
        }

        //构造请求对象
        AddFriendRequestPacket requestPacket = new AddFriendRequestPacket();
        requestPacket.setToUserId(toUserId);
        requestPacket.setFromUserId(fromUserId);
        requestPacket.setFromUsername(fromUsername);
        requestPacket.setRequestMessage(requestMessage);

        channel.writeAndFlush(requestPacket);
    }
}
