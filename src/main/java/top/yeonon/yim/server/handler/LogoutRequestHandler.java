package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutResponsePacket;
import top.yeonon.yim.util.SessionUtil;

/**
 *
 * 登出请求处理器
 * @Author yeonon
 * @date 2018/11/15 0015 20:37
 **/
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
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
