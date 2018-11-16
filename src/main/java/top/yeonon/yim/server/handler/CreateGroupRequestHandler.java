package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupRequestPacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupResponsePacket;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:33
 **/
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {


    private final static AtomicLong id = new AtomicLong(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> usernameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (long userId : createGroupRequestPacket.getUserIdSet()) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null && SessionUtil.hasLogin(channel)) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        long groupId = id.getAndIncrement();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUsernameList(usernameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        //绑定group
        GroupUtil.bindChannelGroup(groupId, channelGroup);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUsernameList());
    }
}
