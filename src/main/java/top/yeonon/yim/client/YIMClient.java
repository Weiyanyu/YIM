package top.yeonon.yim.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.yeonon.yim.client.handler.LoginResponseHandler;
import top.yeonon.yim.client.handler.LogoutResponseHandler;
import top.yeonon.yim.client.handler.SingleMessageResponseHandler;
import top.yeonon.yim.handler.PacketDecoder;
import top.yeonon.yim.handler.PacketEncoder;
import top.yeonon.yim.protocol.command.CommandExecutorManager;
import top.yeonon.yim.protocol.command.LoginCommandExecutor;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;
import top.yeonon.yim.util.SessionUtil;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 17:24
 **/
public final class YIMClient {

    private static final int MAX_RETRY = 5;

    private static void init(String host, int port, int retry) {

        Bootstrap client = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();

        client.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new PacketDecoder());
                        pipeline.addLast(new LoginResponseHandler());

                        //下面的handler，除了encoder之外，其他的没有顺序要求
                        pipeline.addLast(new LogoutResponseHandler());
                        pipeline.addLast(new SingleMessageResponseHandler());

                        pipeline.addLast(new PacketEncoder());

                    }
                });

        connect(client, host, port, retry);

    }

    private static void connect(Bootstrap client, String host, int port, int retry) {
        client.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("客户端连接成功！");
                Channel channel = ((ChannelFuture) future).channel();
                startCommandLine(channel);
            } else if (retry == 0) {
                System.out.println("连接失败！放弃连接！");
            } else {
                int remain = (retry - MAX_RETRY) + 1;
                int delay = 1 << remain;
                System.out.println("客户端连接失败，第" + remain + "次重试");
                client.config().group().schedule(() -> connect(client, host, port, retry - 1),
                        delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startCommandLine(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!Thread.currentThread().isInterrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    LoginCommandExecutor.INSTANCE.exec(scanner, channel);
                } else {
                    CommandExecutorManager.INSTANCE.exec(scanner, channel);
                }
            }

        }).start();
    }



    public static void main(String[] args) {
        YIMClient.init("localhost", 8000, 5);
    }

}
