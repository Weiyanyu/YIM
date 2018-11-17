package top.yeonon.yim.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 11:53
 **/
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int TIME_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        sendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void sendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                sendHeartBeat(ctx);
            }
        }, TIME_INTERVAL, TimeUnit.SECONDS);
    }
}
