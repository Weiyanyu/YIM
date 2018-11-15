package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.yeonon.yim.util.SessionUtil;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:17
 **/
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接验证成功，不需要再次验证");
        } else {
            System.out.println("无登录验证，关闭连接");
        }
    }
}
