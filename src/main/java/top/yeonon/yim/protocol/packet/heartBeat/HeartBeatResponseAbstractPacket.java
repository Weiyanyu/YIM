package top.yeonon.yim.protocol.packet.heartBeat;

import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 11:48
 **/
public class HeartBeatResponseAbstractPacket extends AbstractPacket {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_RESPONSE.getCode();
    }
}
