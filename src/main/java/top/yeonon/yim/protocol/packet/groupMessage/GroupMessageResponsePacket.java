package top.yeonon.yim.protocol.packet.groupMessage;

import lombok.Data;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:24
 **/
@Data
public class GroupMessageResponsePacket extends Packet {

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
