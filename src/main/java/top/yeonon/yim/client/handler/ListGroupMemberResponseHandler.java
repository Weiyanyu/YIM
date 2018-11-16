package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberResponsePacket;

/**
 * 群组成员列表
 * @Author yeonon
 * @date 2018/11/16 0016 13:44
 **/
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket listGroupMemberResponsePacket) throws Exception {
        if (listGroupMemberResponsePacket.isSuccess()) {
            System.out.println("群[" + listGroupMemberResponsePacket.getGroupId() + "]中的人包括：" + listGroupMemberResponsePacket.getSessionSet());
        } else {
            System.out.println(listGroupMemberResponsePacket.getErrorReason());
        }

        ctx.channel().attr(Attributes.FINISHED_TASK).set(true);
    }
}
