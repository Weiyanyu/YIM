package top.yeonon.yim.protocol.packet.listFriend;

import lombok.Data;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.pojo.User;
import top.yeonon.yim.protocol.packet.Packet;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:31
 **/
@Data
public class ListFriendsResponsePacket extends Packet {

    private Set<String> usernames;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LIST_FRIENDS_RESPONSE.getCode();
    }
}
