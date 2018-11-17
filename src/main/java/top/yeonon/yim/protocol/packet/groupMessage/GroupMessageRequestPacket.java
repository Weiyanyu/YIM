package top.yeonon.yim.protocol.packet.groupMessage;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:22
 **/
@Data
public class GroupMessageRequestPacket extends Packet {

    private long toGroupId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST.getCode();
    }
}
