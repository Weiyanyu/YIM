package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponsePacket;
import top.yeonon.yim.util.GroupUtil;

/**
 * 退出群
 * @Author yeonon
 * @date 2018/11/16 0016 14:49
 **/
@ChannelHandler.Sharable
public class QuiteGroupRequestHandler extends SimpleChannelInboundHandler<QuiteGroupRequestPacket> {


    public static final QuiteGroupRequestHandler INSTANCE = new QuiteGroupRequestHandler();

    private QuiteGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuiteGroupRequestPacket requestPacket) throws Exception {
        long groupId = requestPacket.getGroupId();
        ChannelGroup group = GroupUtil.getChannelGroup(groupId);

        //把channel从group里remove就行了
        group.remove(ctx.channel());
        //如果客户端退出之后，群组已经没人了，应该要把群组删除
        //在我们的逻辑中，解绑即可
        if (group.size() == 0) {
            GroupUtil.unBindChannelGroup(groupId);
        }

        //构造响应对象
        QuiteGroupResponsePacket responsePacket = new QuiteGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
