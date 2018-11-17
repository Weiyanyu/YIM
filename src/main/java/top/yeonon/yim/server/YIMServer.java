package top.yeonon.yim.server;


import com.sun.corba.se.impl.interceptors.PICurrent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.yeonon.yim.client.handler.HeartBeatTimerHandler;
import top.yeonon.yim.handler.*;
import top.yeonon.yim.server.handler.*;


/**
 * @Author yeonon
 * @date 2018/11/15 0015 17:25
 **/
public final class YIMServer {


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

        bind(server, port, bindRetry);

    }

    private static void bind(ServerBootstrap server, int port, boolean bindRetry) {
        server.bind(port).addListener((future -> {
            if (future.isSuccess()) {
                System.out.println("绑定端口成功！服务端正在监听端口：" + port);
            } else {
                if (bindRetry) {
                    System.out.println("绑定端口失败！重试其他端口");
                    bind(server, port+1, bindRetry);
                } else {
                    System.out.println("绑定端口失败！服务端启动失败！");
                }
            }
        }));
    }



    public static void main(String[] args) {
        int port = 8000;
        YIMServer.start(port, true);
    }

}
