package top.yeonon.yim.protocol.packet.addFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:34
 **/
@Data
public class AddFriendRequestAbstractPacket extends AbstractPacket {

    private long toUserId;

    private long fromUserId;
    private String fromUsername;

    private String requestMessage;

    @Override
    public byte getCommand() {
        return Command.ADD_FRIEND_REQUEST.getCode();
    }
}
