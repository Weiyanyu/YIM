package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestPacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:53
 **/
public class QuiteGroupCommandExecutor implements CommandExecutor {

    public static final QuiteGroupCommandExecutor INSTANCE;

    static {
        INSTANCE = new QuiteGroupCommandExecutor();
    }

    private QuiteGroupCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【退出群组】输入 groupId：");
        long groupId = scanner.nextLong();

        //构造请求对象
        QuiteGroupRequestPacket requestPacket = new QuiteGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
