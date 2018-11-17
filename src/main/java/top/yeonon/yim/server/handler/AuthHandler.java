package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.yeonon.yim.util.SessionUtil;

/**
 *
 * 权限认证
 * @Author yeonon
 * @date 2018/11/15 0015 20:17
 **/
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果用户没有登录，表示没有权限进行后面的请求，直接关闭连接
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            //验证通过之后，可以移除该Handler了，后续不需要重复验证
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
