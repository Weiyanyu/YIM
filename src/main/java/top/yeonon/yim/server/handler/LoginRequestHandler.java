package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.mapper.UserMapper;
import top.yeonon.yim.persistent.pojo.User;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;
import top.yeonon.yim.protocol.packet.login.LoginResponsePacket;
import top.yeonon.yim.util.DataBaseUtil;
import top.yeonon.yim.util.SessionUtil;
/**
 * 登录请求处理器
 * @Author yeonon
 * @date 2018/11/15 0015 18:58
 **/
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(requestPacket.getVersion());

        String username = requestPacket.getUsername();

        //验证通过后才是登录成功
        if (validate(requestPacket, responsePacket)) {
            //构造响应对象，设置id和username
            responsePacket.setSuccess(true);
            responsePacket.setVersion(requestPacket.getVersion());
            //将Session和Channel绑定，Session即表示用户会话
            SessionUtil.bindSession(new Session(responsePacket.getUser().getId(), username),
                                   ctx.channel());
            System.out.println("[" + username + "]登录成功");
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setErrorReason("登录失败，请检查用户名和密码！");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }


    /**
     * 验证用户名和密码
     * @param requestPacket
     * @return
     */
    private boolean validate(LoginRequestPacket requestPacket, LoginResponsePacket responsePacket) {
        //sqlSession最好是线程私有的，而且用完之后要关闭，避免资源泄露
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserByUsernameAndPassword(requestPacket.getUsername(),
                    requestPacket.getPassword());
            if (user != null) {
                //将user对象放到response里
                user.setPassword("");
                responsePacket.setUser(user);
                return true;
            }
            return false;
        }
    }
}
