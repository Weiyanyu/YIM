package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageRequestPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponsePacket;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 15:26
 **/
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        long groupId = requestPacket.getToGroupId();
        ChannelGroup group = GroupUtil.getChannelGroup(groupId);
        Session session = SessionUtil.getSession(ctx.channel());

        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        if (!group.contains(ctx.channel())) {
            responsePacket.setSuccess(false);
            responsePacket.setErrorReason("您没有再该群组里，请先加入群组");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        responsePacket.setSuccess(true);
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUsername(session.getUsername());
        responsePacket.setMessage(requestPacket.getMessage());

        group.writeAndFlush(responsePacket);
    }
}
