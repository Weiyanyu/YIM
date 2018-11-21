package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponseAbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:59
 **/
public class AddFriendResponseHandler extends SimpleChannelInboundHandler<AddFriendResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponseAbstractPacket responsePacket) throws Exception {
        //直接打印消息就行，注意如果添加成功的话，双发都能收到消息，如果失败，就只有请求发收到消息
        if (responsePacket.isSuccess()) {
            System.out.println("添加好友成功，你们现在可以一起聊天了！");
        } else {
            System.out.println("添加好友失败！原因是：" + responsePacket.getErrorMessage());
        }
    }
}
