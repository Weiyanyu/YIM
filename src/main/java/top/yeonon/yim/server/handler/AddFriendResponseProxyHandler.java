package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.mapper.FriendListMapper;
import top.yeonon.yim.pojo.FriendList;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:57
 **/
@ChannelHandler.Sharable
public class AddFriendResponseProxyHandler extends SimpleChannelInboundHandler<AddFriendResponsePacket> {

    public static final AddFriendResponseProxyHandler INSTANCE = new AddFriendResponseProxyHandler();

    private AddFriendResponseProxyHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponsePacket responsePacket) throws Exception {
        long toUserId = responsePacket.getToUserId();
        long fromUserId = responsePacket.getFromUserId();

        String toUsername = responsePacket.getToUsername();
        String fromUsername = responsePacket.getFromUsername();

        Channel fromChannel = SessionUtil.getChannel(fromUserId);
        Channel toChannel = SessionUtil.getChannel(toUserId);
        fromChannel.writeAndFlush(responsePacket);
        toChannel.writeAndFlush(responsePacket);

        addFriendShip(toUserId, toUsername, fromUserId, fromUsername);
    }

    private void addFriendShip(long toUserId, String toUsername, long fromUserId, String fromUsername) {
        FriendList friendList1 = new FriendList();
        friendList1.setUserId(toUserId);
        friendList1.setFriendId(fromUserId);
        friendList1.setFriendUsername(fromUsername);

        FriendList friendList2 = new FriendList();
        friendList2.setUserId(fromUserId);
        friendList2.setFriendId(toUserId);
        friendList2.setFriendUsername(toUsername);

        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            FriendListMapper friendListMapper = sqlSession.getMapper(FriendListMapper.class);
            friendListMapper.insert(friendList1);
            friendListMapper.insert(friendList2);
            //事务提交
            sqlSession.commit();
        }
    }
}
