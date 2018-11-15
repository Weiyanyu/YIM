package top.yeonon.yim.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.yeonon.yim.protocol.codec.PacketCodec;
import top.yeonon.yim.protocol.packet.Packet;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 18:59
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, packet);
    }
}
