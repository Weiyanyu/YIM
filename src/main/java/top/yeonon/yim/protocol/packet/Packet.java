package top.yeonon.yim.protocol.packet;

import lombok.Data;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 17:58
 **/
@Data
public abstract class Packet {

    private byte version = 1;

    public abstract byte getCommand();
}
