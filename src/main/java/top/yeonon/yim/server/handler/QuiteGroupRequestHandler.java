package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponsePacket;
import top.yeonon.yim.util.GroupUtil;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 14:49
 **/
public class QuiteGroupRequestHandler extends SimpleChannelInboundHandler<QuiteGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuiteGroupRequestPacket requestPacket) throws Exception {
        long groupId = requestPacket.getGroupId();
        ChannelGroup group = GroupUtil.getChannelGroup(groupId);
        group.remove(ctx.channel());

        QuiteGroupResponsePacket responsePacket = new QuiteGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
