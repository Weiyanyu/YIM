package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.mapper.FriendListMapper;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.List;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:38
 **/
@ChannelHandler.Sharable
public class AddFriendRequestProxyHandler extends SimpleChannelInboundHandler<AddFriendRequestPacket> {

    public static final AddFriendRequestProxyHandler INSTANCE = new AddFriendRequestProxyHandler();

    private AddFriendRequestProxyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendRequestPacket requestPacket) throws Exception {
        long toUserId = requestPacket.getToUserId();
        long fromUserId = requestPacket.getFromUserId();
        Channel toChannel = SessionUtil.getChannel(toUserId);

        //如果用户不在线
        if (toChannel == null || !toChannel.isActive()) {
            AddFriendResponsePacket responsePacket = new AddFriendResponsePacket();
            responsePacket.setSuccess(false);
            responsePacket.setErrorMessage("用户不在线，添加好友失败！");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        //如果已经是好友关系
        if (checkFriendList(fromUserId, toUserId)) {
            AddFriendResponsePacket responsePacket = new AddFriendResponsePacket();
            responsePacket.setSuccess(false);
            responsePacket.setErrorMessage("该用户已经是您的好友，请不要重复发送请求！");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        //转发request请求
        toChannel.writeAndFlush(requestPacket);
    }

    /**
     * 检查两者是否已经是好友关系了
     * @param fromUserId
     * @param toUserId
     * @return
     */
    private boolean checkFriendList(long fromUserId, long toUserId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            FriendListMapper friendListMapper = sqlSession.getMapper(FriendListMapper.class);
            Set<Long> friendIds = friendListMapper.selectFriendIdsByUserId(fromUserId);
            return friendIds.contains(toUserId);
        }
    }
}
