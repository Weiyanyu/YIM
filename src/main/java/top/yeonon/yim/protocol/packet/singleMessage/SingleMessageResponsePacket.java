package top.yeonon.yim.protocol.packet.singleMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleMessageResponsePacket extends Packet {

    private long fromUserId;
    private String fromUsername;
    private String message;

    @Override
    public byte getCommand() {
        return Command.SINGLE_MESSAGE_RESPONSE.getCode();
    }
}
