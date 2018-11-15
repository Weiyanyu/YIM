package top.yeonon.yim.protocol.packet.logout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutResponsePacket extends Packet {

    private String username;

    private Long id;

    private boolean success;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LOGOUT_RESPONSE.getCode();
    }
}
