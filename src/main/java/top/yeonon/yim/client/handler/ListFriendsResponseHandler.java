package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsResponsePacket;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:41
 **/
public class ListFriendsResponseHandler extends SimpleChannelInboundHandler<ListFriendsResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListFriendsResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println(responsePacket.getUsernames());
        } else {
            System.out.println("查询失败！原因是: " + responsePacket.getErrorReason());
        }
    }
}
