package top.yeonon.yim.util;

import io.netty.channel.Channel;
import top.yeonon.yim.common.Attributes;
import top.yeonon.yim.common.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 管理用户登录状态的Session工具类
 *
 * @Author yeonon
 * @date 2018/11/15 0015 19:54
 **/
public final class SessionUtil {

    //ID为key，Channel为value的Map
    private static final Map<Long, Channel> userIdChannelMap;

    static {
        userIdChannelMap = new ConcurrentHashMap<>();
    }

    /**
     * 将Session和Channel绑定
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        //帮Session附着在channel上
        channel.attr(Attributes.LOGIN_SESSION).set(session);
    }

    /**
     * 解绑
     * @param channel
     */
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.LOGIN_SESSION).set(null);
        }
    }

    /**
     * 获取Channel
     * @param userId
     * @return
     */
    public static Channel getChannel(long userId) {
        return userIdChannelMap.get(userId);
    }

    /**
     * 获取Session
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.LOGIN_SESSION).get();
    }

    /**
     * 判断是否处于登录状态
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.LOGIN_SESSION);
    }
}
