package top.yeonon.yim.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import org.apache.log4j.Logger;
import top.yeonon.yim.handler.*;
import top.yeonon.yim.server.handler.*;


/**
 * @Author yeonon
 * @date 2018/11/15 0015 17:25
 **/
public final class YIMServer {

    private static final Logger log = Logger.getLogger(YIMServer.class);

    private static Channel channel = null;

    private static void start(int port, boolean bindRetry) {
        ServerBootstrap server = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        server.group(boss, worker)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new YIMIdleStateHandler());

                        //处理粘包和拆包
                        pipeline.addLast(new Separator(1024, 7, 4));
                        pipeline.addLast(PacketCodecHandler.INSTANCE);
                        pipeline.addLast(LoginRequestHandler.INSTANCE);
                        pipeline.addLast(HeartBeatResponseHandler.INSTANCE);
                        pipeline.addLast(AuthHandler.INSTANCE);

                        //缩短了传播距离
                        //因为其实每次请求只会被一个Handler处理，这样做可以缩短数据的传播路径
                        pipeline.addLast(YIMHandler.INSTANCE);

                    }
                });

        channel = bind(server, port, bindRetry);

    }

    /**
     * 优雅停机
     */
    private static void stop() {
        Future future = channel.eventLoop().shutdownGracefully();

        if (future.isSuccess()) {
            System.out.println("关闭服务器成功！");
        } else {
            System.out.println("关闭服务器失败！");
        }
    }



    private static Channel bind(ServerBootstrap server, int port, boolean bindRetry) {
        return server.bind(port).addListener((future -> {
            if (future.isSuccess()) {
                log.info("绑定端口成功！服务端正在监听端口：" + port);
            } else {
                if (bindRetry) {
                    log.info("绑定端口失败！重试其他端口");
                    bind(server, port+1, bindRetry);
                } else {
                    log.info("绑定端口失败！服务端启动失败！");
                }
            }
        })).channel();
    }



    public static void main(String[] args) {
        int port = 8000;
        YIMServer.start(port, true);

        Runtime.getRuntime().addShutdownHook(new Thread(YIMServer::stop));
    }

}
