package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsRequestPacket;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;

import java.util.List;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/19 0019 18:35
 **/
@ChannelHandler.Sharable
public class ListFriendsRequestHandler extends SimpleChannelInboundHandler<ListFriendsRequestPacket> {

    public static final ListFriendsRequestHandler INSTANCE = new ListFriendsRequestHandler();

    private ListFriendsRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListFriendsRequestPacket requestPacket) throws Exception {
        long userId = requestPacket.getUserId();
        Set<String> usernameSet = getFriends(userId);

        //直接查询即可，即使是空的也没事
        ListFriendsResponsePacket responsePacket = new ListFriendsResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setUsernames(usernameSet);

        ctx.channel().writeAndFlush(responsePacket);
    }

    /**
     * 查询该用户的所有好友的用户名
     * @param userId
     * @return
     */
    private Set<String> getFriends(long userId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            FriendListMapper mapper = sqlSession.getMapper(FriendListMapper.class);
            return mapper.selectFriendUsernameById(userId);
        }
    }
}
