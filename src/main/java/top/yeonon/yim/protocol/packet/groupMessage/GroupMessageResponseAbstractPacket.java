package top.yeonon.yim.protocol.packet.groupMessage;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:24
 **/
@Data
public class GroupMessageResponseAbstractPacket extends AbstractPacket {

    private long fromGroupId;
    private long fromUserId;
    private String fromUsername;
    private String message;

    private boolean success;
    private String errorReason;

    @Override
    public byte getCommand() {

        return Command.GROUP_MESSAGE_RESPONSE.getCode();
    }
}
