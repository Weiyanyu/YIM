package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.util.SessionUtil;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 13:41
 **/
public class AddFriendRequestHandler extends SimpleChannelInboundHandler<AddFriendRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendRequestPacket requestPacket) throws Exception {
        System.out.println(requestPacket.getToUserId() + " 想添加您为好友，是否同意?");
        System.out.println("这是他的好友申请信息： " + requestPacket.getRequestMessage());

        //暂时先默认直接同意
        //TODO 目前因为只是命令行，不方便做确认
        Session session = SessionUtil.getSession(ctx.channel());
        AddFriendResponsePacket responsePacket = new AddFriendResponsePacket();
        responsePacket.setSuccess(true);

        //构造响应对象
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUsername(session.getUsername());
        responsePacket.setToUserId(requestPacket.getFromUserId());
        responsePacket.setToUsername(requestPacket.getFromUsername());

        //发送到服务端
        ctx.channel().writeAndFlush(responsePacket);
    }
}
