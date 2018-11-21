package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.pojo.GroupList;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponseAbstractPacket;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.List;

/**
 * 群组消息处理器
 * @Author yeonon
 * @date 2018/11/16 0016 15:26
 **/
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestAbstractPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {}


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestAbstractPacket requestPacket) throws Exception {
        long groupId = requestPacket.getToGroupId();
        //拿到Session
        Session session = SessionUtil.getSession(ctx.channel());

        //如果该用户不在群组里，不要把消息发送到群组group里
        GroupMessageResponseAbstractPacket responsePacket = new GroupMessageResponseAbstractPacket();
        if (!GroupUtil.checkGroup(session.getUserId(), groupId)) {
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
        List<GroupList> groupLists = GroupUtil.getGroupMember(groupId);
        for (GroupList groupList : groupLists) {
            Channel channel = SessionUtil.getChannel(groupList.getUserId());
            if (channel == null || channel.equals(ctx.channel())) {
                continue;
            }
            channel.writeAndFlush(responsePacket);
        }

    }
}
