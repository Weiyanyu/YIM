package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsRequestAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:42
 **/
public class ListFriendsCommandExecutor implements CommandExecutor {

    public static final ListFriendsCommandExecutor INSTANCE = new ListFriendsCommandExecutor();

    private ListFriendsCommandExecutor() {}

    @Override
    public void exec(Scanner scanner, Channel channel) {

        ListFriendsRequestAbstractPacket requestPacket = new ListFriendsRequestAbstractPacket();
        requestPacket.setUserId(SessionUtil.getSession(channel).getUserId());
        channel.writeAndFlush(requestPacket);
    }
}
