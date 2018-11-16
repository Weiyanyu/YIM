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
 * 群组消息处理器
 * @Author yeonon
 * @date 2018/11/16 0016 15:26
 **/
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        long groupId = requestPacket.getToGroupId();
        ChannelGroup group = GroupUtil.getChannelGroup(groupId);
        //拿到Session
        Session session = SessionUtil.getSession(ctx.channel());

        //如果该用户不在群组里，不要把消息发送到群组group里
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        if (!group.contains(ctx.channel())) {
            responsePacket.setSuccess(false);
            responsePacket.setErrorReason("您没有再该群组里，请先加入群组");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        //填充响应对象
        responsePacket.setSuccess(true);
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUsername(session.getUsername());
        responsePacket.setMessage(requestPacket.getMessage());

        //将消息广播给群组里的成员
        group.writeAndFlush(responsePacket);
    }
}