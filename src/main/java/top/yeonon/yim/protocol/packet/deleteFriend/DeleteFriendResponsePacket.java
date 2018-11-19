package top.yeonon.yim.protocol.packet.deleteFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 17:36
 **/
@Data
public class DeleteFriendResponsePacket extends Packet {

    private long userId;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.DELETE_FRIEND_RESPONSE.getCode();
    }
}
