package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.mapper.GroupListMapper;
import top.yeonon.yim.persistent.mapper.GroupMapper;
import top.yeonon.yim.persistent.pojo.Group;
import top.yeonon.yim.persistent.pojo.GroupList;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupRequestPacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

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
        Session session = SessionUtil.getSession(ctx.channel());

        //加入群组
        GroupUtil.joinGroup(groupId, session);

        //构造响应对象
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }


}
