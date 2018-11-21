package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatResponseAbstractPacket;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 12:00
 **/
@ChannelHandler.Sharable
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatRequestAbstractPacket> {

    public static final HeartBeatResponseHandler INSTANCE = new HeartBeatResponseHandler();

    private HeartBeatResponseHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestAbstractPacket msg) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponseAbstractPacket());
    }
}
