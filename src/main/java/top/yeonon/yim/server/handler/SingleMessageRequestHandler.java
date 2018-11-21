package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponseAbstractPacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:33
 **/
@ChannelHandler.Sharable
public class SingleMessageRequestHandler extends SimpleChannelInboundHandler<SingleMessageRequestAbstractPacket> {

    public static final SingleMessageRequestHandler INSTANCE = new SingleMessageRequestHandler();

    private SingleMessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleMessageRequestAbstractPacket singleMessageRequestPacket) throws Exception {
        long toUserId = singleMessageRequestPacket.getToUserId();
        Channel toUserChannel = SessionUtil.getChannel(toUserId);

        //先确定对方是否在线
        if (toUserChannel == null || !SessionUtil.hasLogin(toUserChannel)) {
            System.err.println("[" + toUserId + "] 不在线，发送失败!");
            return;
        }


        //确定在线之后，再构造响应对象
        SingleMessageResponseAbstractPacket singleMessageResponsePacket = new SingleMessageResponseAbstractPacket();

        //拿到请求发的Session
        Session requestSession = SessionUtil.getSession(ctx.channel());
        //填充响应对象属性
        singleMessageResponsePacket.setFromUserId(requestSession.getUserId());
        singleMessageResponsePacket.setFromUsername(requestSession.getUsername());
        //确定是否是好友
        long fromUserId = SessionUtil.getSession(ctx.channel()).getUserId();
        if (!checkIsFriend(fromUserId, toUserId)) {
            singleMessageResponsePacket.setSuccess(false);
            singleMessageResponsePacket.setErrorReason("您不是对方的好友，无法发送消息！");
            ctx.channel().writeAndFlush(singleMessageResponsePacket);
            return;
        }

        singleMessageResponsePacket.setSuccess(true);
        singleMessageResponsePacket.setMessage(singleMessageRequestPacket.getMessage());

        toUserChannel.writeAndFlush(singleMessageResponsePacket);
    }

    /**
     * 检查请求发送消息的用户和对方是否是好友关系
     * @param fromUserId
     * @param toUserId
     * @return
     */
    private boolean checkIsFriend(long fromUserId, long toUserId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            FriendListMapper friendListMapper = sqlSession.getMapper(FriendListMapper.class);
            Set<Long> friendIds = friendListMapper.selectFriendIdsByUserId(fromUserId);
            return friendIds.contains(toUserId);
        }
    }
}
