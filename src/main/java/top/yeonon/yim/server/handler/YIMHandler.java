package top.yeonon.yim.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 13:20
 **/
@ChannelHandler.Sharable
public class YIMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final YIMHandler INSTANCE = new YIMHandler();

    private static final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    static {
        handlerMap = new ConcurrentHashMap<>();
        handlerMap.put(Command.LOGOUT_REQUEST.getCode(),LogoutRequestHandler.INSTANCE);
        handlerMap.put(Command.SINGLE_MESSAGE_REQUEST.getCode(), SingleMessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST.getCode(), CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_MEMBER_REQUEST.getCode(), ListGroupMemberRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST.getCode(), JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUITE_GROUP_REQUEST.getCode(), QuiteGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST.getCode(), GroupMessageRequestHandler.INSTANCE);

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        //如果遇到不知名的指令，之前的handler肯定会已经处理了，所以这里不需要担心空指针
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
