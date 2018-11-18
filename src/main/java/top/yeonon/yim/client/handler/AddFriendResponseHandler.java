package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:59
 **/
public class AddFriendResponseHandler extends SimpleChannelInboundHandler<AddFriendResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("添加好友成功，你们现在可以一起聊天了！");
        } else {
            System.out.println("添加好友失败！原因是：" + responsePacket.getErrorMessage());
        }
    }
}
