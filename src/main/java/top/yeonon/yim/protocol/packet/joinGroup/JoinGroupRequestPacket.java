package top.yeonon.yim.protocol.packet.joinGroup;

import lombok.Data;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:18
 **/
@Data
public class JoinGroupRequestPacket extends Packet {

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_REQUEST.getCode();
    }
}
