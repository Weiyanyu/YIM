package top.yeonon.yim.protocol.packet.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.persistent.pojo.User;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 18:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseAbstractPacket extends AbstractPacket {

    private User user;

    private boolean isSuccess;

    private String errorReason;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE.getCode();
    }
}
