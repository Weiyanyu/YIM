package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageRequestPacket;

import java.util.Scanner;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:48
 **/
public class SingleMessageCommandExecutor implements CommandExecutor {

    public static final SingleMessageCommandExecutor INSTANCE;

    static {
        INSTANCE = new SingleMessageCommandExecutor();
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        long toUserId = scanner.nextLong();
        String message = scanner.next();

        SingleMessageRequestPacket singleMessageRequestPacket = new SingleMessageRequestPacket();
        singleMessageRequestPacket.setToUserId(toUserId);
        singleMessageRequestPacket.setMessage(message);

        channel.writeAndFlush(singleMessageRequestPacket);
    }
}
