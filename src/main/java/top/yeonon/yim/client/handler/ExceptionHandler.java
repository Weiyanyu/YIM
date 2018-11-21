package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.exception.ExceptionAbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/20 0020 17:45
 **/
public class ExceptionHandler extends SimpleChannelInboundHandler<ExceptionAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExceptionAbstractPacket packet) throws Exception {
        System.out.println("发生异常，原因如下：\n" + packet.getErrorReason());
    }
}
