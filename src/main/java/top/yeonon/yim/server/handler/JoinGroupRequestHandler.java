package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupRequestPacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupResponsePacket;
import top.yeonon.yim.util.GroupUtil;

/**
 *
 * 加入群组
 * @Author yeonon
 * @date 2018/11/16 0016 14:22
 **/
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {

        //将当前channel加入到群组里，即用户进群
        long groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup group = GroupUtil.getChannelGroup(groupId);
        group.add(ctx.channel());

        //构造响应对象
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
