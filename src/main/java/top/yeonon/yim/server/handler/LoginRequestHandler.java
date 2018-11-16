package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;
import top.yeonon.yim.protocol.packet.login.LoginResponsePacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 登录请求处理器
 * @Author yeonon
 * @date 2018/11/15 0015 18:58
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    //用户生成id
    private final static AtomicLong id = new AtomicLong(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        String username = loginRequestPacket.getUsername();
        long userId = id.getAndIncrement();

        //构造响应对象，设置id和username
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setUsername(username);

        //验证通过后才是登录成功
        if (validate(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());

            //将Session和Channel绑定，Session即表示用户会话
            SessionUtil.bindSession(new Session(userId, username),
                                   ctx.channel());

            System.out.println("[" + username + "]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setErrorReason("登录失败，请检查用户名和密码！");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    /**
     * 验证用户名和密码
     * @param loginRequestPacket
     * @return
     */
    private boolean validate(LoginRequestPacket loginRequestPacket) {
        return true; //for-now
    }
}
