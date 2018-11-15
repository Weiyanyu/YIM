package top.yeonon.yim.protocol.packet.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 18:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {

    private String username;
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST.getCode();
    }
}
