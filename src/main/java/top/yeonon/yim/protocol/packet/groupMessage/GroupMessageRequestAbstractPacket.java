package top.yeonon.yim.protocol.packet.groupMessage;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:22
 **/
@Data
public class GroupMessageRequestAbstractPacket extends AbstractPacket {

    private long toGroupId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST.getCode();
    }
}
