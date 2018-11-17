package top.yeonon.yim.protocol.packet.logout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestPacket extends Packet {

    private long userId;
    private String username;

    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST.getCode();
    }
}
