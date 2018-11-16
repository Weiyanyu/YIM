package top.yeonon.yim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageRequestPacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponsePacket;
import top.yeonon.yim.util.SessionUtil;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 21:33
 **/
public class SingleMessageRequestHandler extends SimpleChannelInboundHandler<SingleMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleMessageRequestPacket singleMessageRequestPacket) throws Exception {
        long toUserId = singleMessageRequestPacket.getToUserId();
        Channel toUserChannel = SessionUtil.getChannel(toUserId);

        //先确定对方是否在线
        if (toUserChannel == null || !SessionUtil.hasLogin(toUserChannel)) {
            System.err.println("[" + toUserId + "] 不在线，发送失败!");
            return;
        }

        //确定在线之后，再构造响应对象
        SingleMessageResponsePacket singleMessageResponsePacket = new SingleMessageResponsePacket();

        //拿到请求发的Session
        Session requestSession = SessionUtil.getSession(ctx.channel());
        //填充响应对象属性
        singleMessageResponsePacket.setFromUserId(requestSession.getUserId());
        singleMessageResponsePacket.setFromUsername(requestSession.getUsername());
        singleMessageResponsePacket.setMessage(singleMessageRequestPacket.getMessage());

        toUserChannel.writeAndFlush(singleMessageResponsePacket);
    }
}
