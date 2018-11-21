package top.yeonon.yim.protocol.packet.addFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:34
 **/
@Data
public class AddFriendResponseAbstractPacket extends AbstractPacket {

    private long fromUserId;
    private String fromUsername;

    private long toUserId;
    private String toUsername;

    private boolean success;

    private String errorMessage;

    @Override
    public byte getCommand() {
        return Command.ADD_FRIEND_RESPONSE.getCode();
    }
}
