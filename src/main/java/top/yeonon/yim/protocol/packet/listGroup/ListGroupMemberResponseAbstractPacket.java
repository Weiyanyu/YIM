package top.yeonon.yim.protocol.packet.listGroup;

import lombok.Data;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 13:30
 **/
@Data
public class ListGroupMemberResponseAbstractPacket extends AbstractPacket {

    private long groupId;

    private Set<Session> sessionSet;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBER_RESPONSE.getCode();
    }
}
