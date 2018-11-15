package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponsePacket;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:43
 **/
public class SingleMessageResponseHandler extends SimpleChannelInboundHandler<SingleMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleMessageResponsePacket singleMessageResponsePacket) throws Exception {
        String fromUsername = singleMessageResponsePacket.getFromUsername();
        long fromUserId = singleMessageResponsePacket.getFromUserId();
        String message = singleMessageResponsePacket.getMessage();
        System.out.println(fromUserId + ":" + fromUsername + " -> " + message);
    }
}
