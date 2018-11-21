package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponseAbstractPacket;

/**
 * 群组消息
 * @Author yeonon
 * @date 2018/11/16 0016 15:29
 **/
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponseAbstractPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponseAbstractPacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            long fromUserId = responsePacket.getFromUserId();
            long fromGroupId = responsePacket.getFromGroupId();
            String fromUsername = responsePacket.getFromUsername();
            String message = responsePacket.getMessage();
            System.out.println(fromGroupId + ":" + fromUserId + ":" + fromUsername + " -> " + message);
        } else {
            System.err.println("发送失败! 原因是: " + responsePacket.getErrorReason());
        }

    }
}
