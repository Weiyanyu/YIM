package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponsePacket;

/**
 * 退出群聊
 * @Author yeonon
 * @date 2018/11/16 0016 14:51
 **/
public class QuiteGroupResponseHandler extends SimpleChannelInboundHandler<QuiteGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuiteGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]失败！");
        }

        ctx.channel().attr(Attributes.FINISHED_TASK).set(true);
    }
}
