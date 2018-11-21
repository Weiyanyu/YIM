package top.yeonon.yim.protocol.packet.listGroup;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 13:28
 **/
@Data
public class ListGroupMemberRequestAbstractPacket extends AbstractPacket {

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBER_REQUEST.getCode();
    }
}
