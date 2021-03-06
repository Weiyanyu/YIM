package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
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
import top.yeonon.yim.persistent.pojo.User;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberRequestPacket;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 群组成员列表
 * @Author yeonon
 * @date 2018/11/16 0016 13:33
 **/
@ChannelHandler.Sharable
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {


    public static final ListGroupMemberRequestHandler INSTANCE = new ListGroupMemberRequestHandler();

    private ListGroupMemberRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket listGroupMemberRequestPacket) throws Exception {
        long groupId = listGroupMemberRequestPacket.getGroupId();
        //先拿到channelGroup实例
        Group group = getGroup(groupId);

        ListGroupMemberResponsePacket listGroupMemberResponsePacket = new ListGroupMemberResponsePacket();

        //如果不存在该群组，就返回错误消息即可
        if (group == null) {
            listGroupMemberResponsePacket.setErrorReason("不存在该群组，请检查群组ID");
            listGroupMemberResponsePacket.setSuccess(false);
            ctx.channel().writeAndFlush(listGroupMemberResponsePacket);
            return;
        }

        //把群组里的用户信息放入到Set集合里
        Set<Session> sessionSet = new HashSet<>();
    for (GroupList groupList : getGroupMember(groupId)) {
            sessionSet.add(new Session(groupList.getUserId(), groupList.getUsername()));
        }
        //填充响应对象
        listGroupMemberResponsePacket.setSuccess(true);
        listGroupMemberResponsePacket.setSessionSet(sessionSet);
        listGroupMemberResponsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(listGroupMemberResponsePacket);
    }

    private Group getGroup(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
            return mapper.selectByPrimaryKey(groupId);
        }
    }

    private List<GroupList> getGroupMember(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);
            return mapper.selectGroupListByGroupId(groupId);
        }
    }
}
