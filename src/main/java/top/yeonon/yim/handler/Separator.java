package top.yeonon.yim.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import top.yeonon.yim.protocol.codec.PacketCodec;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:55
 **/
public class Separator extends LengthFieldBasedFrameDecoder {

    public Separator(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //使用ByteBuf.getInt()不会改变readerIndex指针
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
