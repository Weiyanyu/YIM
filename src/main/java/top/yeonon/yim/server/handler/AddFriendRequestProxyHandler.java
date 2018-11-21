package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponseAbstractPacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.Set;

/**
 * 添加好友的代理，相当于一个中转站
 * @Author yeonon
 * @date 2018/11/18 0018 13:38
 **/
@ChannelHandler.Sharable
public class AddFriendRequestProxyHandler extends SimpleChannelInboundHandler<AddFriendRequestAbstractPacket> {

    public static final AddFriendRequestProxyHandler INSTANCE = new AddFriendRequestProxyHandler();

    private AddFriendRequestProxyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendRequestAbstractPacket requestPacket) throws Exception {
        //先拿到对方和己方的ID
        long toUserId = requestPacket.getToUserId();
        long fromUserId = requestPacket.getFromUserId();
        //拿到对方的channel
        Channel toChannel = SessionUtil.getChannel(toUserId);

        //如果用户不在线，直接把消息发送回发送方
        if (toChannel == null || !toChannel.isActive()) {
            AddFriendResponseAbstractPacket responsePacket = new AddFriendResponseAbstractPacket();
            responsePacket.setSuccess(false);
            responsePacket.setErrorMessage("用户不在线，添加好友失败！");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        //如果已经是好友关系，同样直接把消息发送回发送方
        if (checkFriendList(fromUserId, toUserId)) {
            AddFriendResponseAbstractPacket responsePacket = new AddFriendResponseAbstractPacket();
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
