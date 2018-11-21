package top.yeonon.yim.protocol.packet.singleMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleMessageRequestAbstractPacket extends AbstractPacket {

    private long toUserId;
    private String message;

    @Override
    public byte getCommand() {
        return Command.SINGLE_MESSAGE_REQUEST.getCode();
    }
}
