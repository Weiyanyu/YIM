package top.yeonon.yim.protocol.packet.listGroup;

import lombok.Data;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 13:28
 **/
@Data
public class ListGroupMemberRequestPacket extends Packet {

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBER_REQUEST.getCode();
    }
}
