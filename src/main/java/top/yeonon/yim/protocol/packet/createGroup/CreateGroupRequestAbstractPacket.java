package top.yeonon.yim.protocol.packet.createGroup;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:25
 **/
@Data
public class CreateGroupRequestAbstractPacket extends AbstractPacket {

    private Set<Long> userIdSet;

    private String groupName;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST.getCode();
    }
}
