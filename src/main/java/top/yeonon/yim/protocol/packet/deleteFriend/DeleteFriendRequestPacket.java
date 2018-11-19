package top.yeonon.yim.protocol.packet.deleteFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 17:35
 **/
@Data
public class DeleteFriendRequestPacket extends Packet {

    private long userId;

    @Override
    public byte getCommand() {
        return Command.DELETE_FRIEND_REQUEST.getCode();
    }
}
