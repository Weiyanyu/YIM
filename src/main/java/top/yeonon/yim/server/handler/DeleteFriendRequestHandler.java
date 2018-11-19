package top.yeonon.yim.server.handler;

import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendRequestPacket;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 17:39
 **/
@ChannelHandler.Sharable
public class DeleteFriendRequestHandler extends SimpleChannelInboundHandler<DeleteFriendRequestPacket> {

    public static final DeleteFriendRequestHandler INSTANCE = new DeleteFriendRequestHandler();

    private DeleteFriendRequestHandler() {}


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeleteFriendRequestPacket requestPacket) throws Exception {
        //拿到双方ID
        long toUserId = requestPacket.getUserId();
        long fromUserId = SessionUtil.getSession(ctx.channel()).getUserId();
        DeleteFriendResponsePacket responsePacket = new DeleteFriendResponsePacket();

        //如果两者不是好友关系，那就没有必要删除什么了
        if (!checkFriendList(fromUserId, toUserId)) {
            responsePacket.setSuccess(false);
            responsePacket.setErrorReason("你俩不是好友关系！！！");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        //这里不太适合做异步处理
        if (!deleteFriendShip(fromUserId, toUserId)) {
            responsePacket.setSuccess(false);
            responsePacket.setErrorReason("数据库异常！");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }

        responsePacket.setSuccess(true);
        responsePacket.setUserId(toUserId);
        ctx.channel().writeAndFlush(responsePacket);

    }

    /**
     * 检查好友关系
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

    /**
     * 删除好友关系
     * @param fromUserId
     * @param toUserId
     * @return
     */
    private boolean deleteFriendShip(long fromUserId, long toUserId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            FriendListMapper friendListMapper = sqlSession.getMapper(FriendListMapper.class);
            int rowCount = friendListMapper.deleteFriendShip(Lists.newArrayList(fromUserId, toUserId));
            sqlSession.commit();
            return rowCount >= 0;
        }


    }
}
