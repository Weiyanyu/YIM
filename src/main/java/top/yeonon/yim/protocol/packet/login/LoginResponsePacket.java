package top.yeonon.yim.protocol.packet.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 18:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponsePacket extends Packet {

    private long userId;

    private String username;

    private boolean isSuccess;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE.getCode();
    }
}
