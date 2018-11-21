package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.logout.LogoutResponseAbstractPacket;

/**
 * 登出响应处理器
 * @Author yeonon
 * @date 2018/11/15 0015 20:48
 **/
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponseAbstractPacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            System.out.println(logoutResponsePacket.getUsername() + ", 您已登出系统");
            //关闭channel连接
            ctx.channel().close();
            //关闭客户端的eventLoop,平稳的关闭客户端
            ctx.channel().eventLoop().shutdownGracefully();
        }

    }
}
