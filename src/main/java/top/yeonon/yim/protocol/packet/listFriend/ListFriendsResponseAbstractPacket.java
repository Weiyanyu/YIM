package top.yeonon.yim.protocol.packet.listFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:31
 **/
@Data
public class ListFriendsResponseAbstractPacket extends AbstractPacket {

    private Set<String> usernames;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LIST_FRIENDS_RESPONSE.getCode();
    }
}
