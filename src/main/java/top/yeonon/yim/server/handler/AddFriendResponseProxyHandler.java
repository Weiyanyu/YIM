package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.persistent.pojo.FriendList;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;

/**
 *
 * 代理器，相当于中转站
 * @Author yeonon
 * @date 2018/11/18 0018 13:57
 **/
@ChannelHandler.Sharable
public class AddFriendResponseProxyHandler extends SimpleChannelInboundHandler<AddFriendResponsePacket> {

    public static final AddFriendResponseProxyHandler INSTANCE = new AddFriendResponseProxyHandler();

    private AddFriendResponseProxyHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponsePacket responsePacket) throws Exception {
        //先拿到对方和己方的ID和username
        long toUserId = responsePacket.getToUserId();
        long fromUserId = responsePacket.getFromUserId();
        String toUsername = responsePacket.getToUsername();
        String fromUsername = responsePacket.getFromUsername();

        //拿到双发的channel，并把消息传递给双方
        Channel fromChannel = SessionUtil.getChannel(fromUserId);
        Channel toChannel = SessionUtil.getChannel(toUserId);
        fromChannel.writeAndFlush(responsePacket);
        toChannel.writeAndFlush(responsePacket);

        //把耗时的数据库任务扔到线程池中异步执行
        ctx.executor().submit(() -> {
            addFriendShip(toUserId, toUsername, fromUserId, fromUsername);
        });
    }

    /**
     * 往好友关系表中添加双方的好友关系
     * @param toUserId
     * @param toUsername
     * @param fromUserId
     * @param fromUsername
     */
    private void addFriendShip(long toUserId, String toUsername, long fromUserId, String fromUsername) {
        //这里的逻辑可能有点绕，但只要弄清楚整个添加好友的逻辑就行了
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
