package top.yeonon.yim.protocol.packet.exception;

import lombok.Data;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/20 0020 17:46
 **/
@Data
public class ExceptionPacket extends Packet {

    private String errorReason;

    @Override
    public byte getCommand() {
        return 0; //0表示异常
    }
}
