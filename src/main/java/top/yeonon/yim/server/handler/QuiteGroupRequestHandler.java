package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponseAbstractPacket;
import top.yeonon.yim.util.GroupUtil;
import top.yeonon.yim.util.SessionUtil;

/**
 * 退出群
 * @Author yeonon
 * @date 2018/11/16 0016 14:49
 **/
@ChannelHandler.Sharable
public class QuiteGroupRequestHandler extends SimpleChannelInboundHandler<QuiteGroupRequestAbstractPacket> {


    public static final QuiteGroupRequestHandler INSTANCE = new QuiteGroupRequestHandler();

    private QuiteGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuiteGroupRequestAbstractPacket requestPacket) throws Exception {
        long groupId = requestPacket.getGroupId();

        //把channel从group里remove就行了
        GroupUtil.quiteGroup(SessionUtil.getSession(ctx.channel()).getUserId(), groupId);
        //如果客户端退出之后，群组已经没人了，应该要把群组删除
        //在我们的逻辑中，解绑即可
        if (GroupUtil.getGroupMemberCount(groupId) == 0) {
            GroupUtil.deleteGroup(groupId);
        }

        //构造响应对象
        QuiteGroupResponseAbstractPacket responsePacket = new QuiteGroupResponseAbstractPacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }


}
