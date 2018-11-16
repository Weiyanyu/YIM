package top.yeonon.yim.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import top.yeonon.yim.protocol.codec.PacketCodec;
import top.yeonon.yim.protocol.packet.Packet;

import java.util.List;

/**
 *
 * 解码器
 * @Author yeonon
 * @date 2018/11/15 0015 19:01
 **/
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(in));
    }
}
