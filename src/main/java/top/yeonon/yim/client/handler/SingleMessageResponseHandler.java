package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponsePacket;

/**
 * 单聊消息响应处理器
 * @Author yeonon
 * @date 2018/11/15 0015 21:43
 **/
public class SingleMessageResponseHandler extends SimpleChannelInboundHandler<SingleMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleMessageResponsePacket singleMessageResponsePacket) throws Exception {
        //仅仅简单的打印消息即可
        String fromUsername = singleMessageResponsePacket.getFromUsername();
        long fromUserId = singleMessageResponsePacket.getFromUserId();
        if (singleMessageResponsePacket.isSuccess()) {
            String message = singleMessageResponsePacket.getMessage();
            System.out.println(fromUserId + ":" + fromUsername + " -> " + message);
        } else {
            System.out.println("发送消息失败！原因是：" + singleMessageResponsePacket.getErrorReason());
        }

    }
}
