package top.yeonon.yim.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupRequestAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:30
 **/
public class CreateGroupCommandExecutor implements CommandExecutor {

    private static final String SEPARATOR = ",";

    public static final CreateGroupCommandExecutor INSTANCE;

    static {
        INSTANCE = new CreateGroupCommandExecutor();
    }

    private CreateGroupCommandExecutor() {

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String[] ids = scanner.next().split(SEPARATOR);
        System.out.print("请输入群组名称： ");
        String groupName = scanner.next();
        Set<Long> userIdSet = new HashSet<>();
        //将id填充到Set里
        for (String id : ids) {
            userIdSet.add(Long.parseLong(id));
        }
        //将自己的id也加入到集合里
        userIdSet.add(SessionUtil.getSession(channel).getUserId());

        CreateGroupRequestAbstractPacket createGroupRequestPacket = new CreateGroupRequestAbstractPacket();
        createGroupRequestPacket.setUserIdSet(userIdSet);
        createGroupRequestPacket.setGroupName(groupName);

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
