package top.yeonon.yim.protocol.packet.createGroup;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

import java.util.List;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:27
 **/
@Data
public class CreateGroupResponsePacket extends Packet {


    private List<String> usernameList;
    private long groupId;
    private String groupName;

    private boolean success;
    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE.getCode();
    }
}
