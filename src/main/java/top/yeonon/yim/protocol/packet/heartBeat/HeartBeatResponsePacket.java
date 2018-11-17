package top.yeonon.yim.protocol.packet.heartBeat;

import top.yeonon.yim.protocol.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 11:48
 **/
public class HeartBeatResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_RESPONSE.getCode();
    }
}
