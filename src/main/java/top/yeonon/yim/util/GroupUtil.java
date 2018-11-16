package top.yeonon.yim.util;

import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yeonon
 * @date 2018/11/16 0016 13:34
 **/
public final class GroupUtil {

    private final static Map<Long, ChannelGroup> channelGroupMaps;

    static {
        channelGroupMaps = new ConcurrentHashMap<>();
    }

    public static void bindChannelGroup(long id, ChannelGroup group) {
        channelGroupMaps.put(id, group);
    }

    public static ChannelGroup getChannelGroup(long id) {
        return channelGroupMaps.get(id);
    }

    public static void unBindChannelGroup(long id) {
        channelGroupMaps.remove(id);
    }
}
