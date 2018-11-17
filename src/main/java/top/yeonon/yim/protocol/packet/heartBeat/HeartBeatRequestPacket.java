package top.yeonon.yim.protocol.packet.heartBeat;

import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 11:46
 **/
public class HeartBeatRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_REQUEST.getCode();
    }
}
