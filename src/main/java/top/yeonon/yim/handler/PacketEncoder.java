package top.yeonon.yim.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.yeonon.yim.protocol.codec.PacketCodec;
import top.yeonon.yim.protocol.packet.AbstractPacket;

/**
 *
 * 编码器
 * @Author yeonon
 * @date 2018/11/15 0015 18:59
 **/
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket abstractPacket, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, abstractPacket);
    }
}
