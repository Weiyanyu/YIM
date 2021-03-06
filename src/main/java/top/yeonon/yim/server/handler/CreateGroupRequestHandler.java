package top.yeonon.yim.server.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.mapper.GroupListMapper;
import top.yeonon.yim.persistent.mapper.GroupMapper;
import top.yeonon.yim.persistent.pojo.Group;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupRequestPacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 12:33
 **/
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> usernameList = Lists.newArrayList();
        //channelGroup即channel集合
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        Map<Long, String> userInfo = Maps.newHashMap();

        //遍历id，将channel放入到channelGroup里
        for (long userId : createGroupRequestPacket.getUserIdSet()) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null && SessionUtil.hasLogin(channel)) {
                channelGroup.add(channel);
                userInfo.put(userId, SessionUtil.getSession(channel).getUsername());
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        long groupId = GroupUtil.createGroup(createGroupRequestPacket.getGroupName(), userInfo);

        //填充响应对象
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUsernameList(usernameList);
        createGroupResponsePacket.setGroupName(createGroupRequestPacket.getGroupName());

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUsernameList());
    }


}
