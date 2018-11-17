package top.yeonon.yim.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 11:50
 **/
public class YIMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public YIMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "s 内没有收到数据，关闭连接");
        ctx.channel().close();
    }
}
