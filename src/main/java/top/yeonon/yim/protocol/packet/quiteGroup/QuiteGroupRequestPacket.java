package top.yeonon.yim.protocol.packet.quiteGroup;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:47
 **/
@Data
public class QuiteGroupRequestPacket extends Packet {

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.QUITE_GROUP_REQUEST.getCode();
    }
}
