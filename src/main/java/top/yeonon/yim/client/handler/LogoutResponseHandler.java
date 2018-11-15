package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.logout.LogoutResponsePacket;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:48
 **/
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            System.out.println(logoutResponsePacket.getUsername() + ", 您已登出系统");
            ctx.channel().close();
            ctx.channel().eventLoop().shutdownGracefully();
        }
    }
}
