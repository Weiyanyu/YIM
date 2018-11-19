package top.yeonon.yim.protocol.packet.listFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:31
 **/
@Data
public class ListFriendsRequestPacket extends Packet {

    private long userId;

    @Override
    public byte getCommand() {
        return Command.LIST_FRIENDS_REQUEST.getCode();
    }
}
