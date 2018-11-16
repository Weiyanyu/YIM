package top.yeonon.yim.protocol.packet.joinGroup;

import lombok.Data;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:19
 **/
@Data
public class JoinGroupResponsePacket extends Packet {

    private long groupId;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE.getCode();
    }
}
