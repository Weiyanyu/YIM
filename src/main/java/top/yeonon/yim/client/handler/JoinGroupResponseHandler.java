package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupResponseAbstractPacket;

/**
 * 加群
 * @Author yeonon
 * @date 2018/11/16 0016 14:25
 **/
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponseAbstractPacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            System.out.println("加入群[" + joinGroupResponsePacket.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + joinGroupResponsePacket.getGroupId() + "]失败，原因为：" + joinGroupResponsePacket.getErrorReason());
        }

    }
}
