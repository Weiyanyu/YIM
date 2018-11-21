package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutResponseAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

/**
 *
 * 登出请求处理器
 * @Author yeonon
 * @date 2018/11/15 0015 20:37
 **/
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestAbstractPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestAbstractPacket logoutRequestPacket) throws Exception {
        LogoutResponseAbstractPacket logoutResponsePacket = new LogoutResponseAbstractPacket();
        long userId = logoutRequestPacket.getUserId();
        String username = logoutRequestPacket.getUsername();

        //构造登出响应对象
        logoutResponsePacket.setId(userId);
        logoutResponsePacket.setUsername(username);
        logoutResponsePacket.setSuccess(true);

        //注意把客户端的Session和channel解绑
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("[" + username + "]登出成功");
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
