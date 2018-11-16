package top.yeonon.yim.protocol.packet.quiteGroup;

import lombok.Data;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:48
 **/
@Data
public class QuiteGroupResponsePacket extends Packet {

    private long groupId;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.QUITE_GROUP_RESPONSE.getCode();
    }
}
