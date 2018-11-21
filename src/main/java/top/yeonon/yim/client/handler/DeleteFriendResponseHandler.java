package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendResponseAbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 17:43
 **/

public class DeleteFriendResponseHandler extends SimpleChannelInboundHandler<DeleteFriendResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeleteFriendResponseAbstractPacket responsePacket) throws Exception {
        //成功或者失败，都给客户端一个通知
        if (responsePacket.isSuccess()) {
            System.out.println("您已经与[" + responsePacket.getUserId() + "]解除好友关系");
        } else {
            System.out.println("删除好友失败！原因是：" + responsePacket.getErrorReason());
        }
    }
}
