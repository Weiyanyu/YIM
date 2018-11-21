package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.login.LoginResponseAbstractPacket;
import top.yeonon.yim.util.SessionUtil;

/**
 *
 * 登录响应处理器
 *
 * @Author yeonon
 * @date 2018/11/15 0015 19:11
 **/
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseAbstractPacket loginResponsePacket) throws Exception {
        //如果登录请求是成功的，就打印消息
        if (loginResponsePacket.isSuccess()) {
            String username = loginResponsePacket.getUser().getUsername();
            long userId = loginResponsePacket.getUser().getId();
            System.out.println("登录成功，欢迎回来，" + username);
            System.out.println("您的ID是： " + userId);

            //这里也要绑定Session和Channel，因为客户端的Channel和服务器Channel并不是同一个
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println("登录失败，" + loginResponsePacket.getErrorReason());
        }
        ctx.channel().attr(Attributes.FINISHED_TASK).set(true);
    }
}
