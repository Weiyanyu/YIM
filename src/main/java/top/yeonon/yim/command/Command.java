package top.yeonon.yim.command;

import top.yeonon.yim.protocol.packet.AbstractPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.exception.ExceptionAbstractPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.login.LoginRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.login.LoginResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponseAbstractPacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageRequestAbstractPacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponseAbstractPacket;

/**
 * 命令枚举类，枚举实例代表了各个命令的代码以及名称
 *
 * @Author yeonon
 * @date 2018/11/15 0015 18:00
 **/
public enum Command {

    //0号表示异常
    EXCEPTION((byte)0, "exception", ExceptionAbstractPacket.class),

    LOGIN_REQUEST((byte) 1, "login", LoginRequestAbstractPacket.class),
    LOGIN_RESPONSE((byte)2, "login", LoginResponseAbstractPacket.class),
    LOGOUT_REQUEST((byte)3, "logout", LogoutRequestAbstractPacket.class),
    LOGOUT_RESPONSE((byte)4, "logout", LogoutResponseAbstractPacket.class),
    SINGLE_MESSAGE_REQUEST((byte)5, "sendToUser", SingleMessageRequestAbstractPacket.class),
    SINGLE_MESSAGE_RESPONSE((byte)6, "sendToUser", SingleMessageResponseAbstractPacket.class),
    CREATE_GROUP_REQUEST((byte)7, "createGroup", CreateGroupRequestAbstractPacket.class),
    CREATE_GROUP_RESPONSE((byte)8, "createGroup", CreateGroupResponseAbstractPacket.class),
    LIST_GROUP_MEMBER_REQUEST((byte)9, "listGroup", ListGroupMemberRequestAbstractPacket.class),
    LIST_GROUP_MEMBER_RESPONSE((byte)10, "listGroup", ListGroupMemberResponseAbstractPacket.class),
    JOIN_GROUP_REQUEST((byte)11, "joinGroup", JoinGroupRequestAbstractPacket.class),
    JOIN_GROUP_RESPONSE((byte)12, "joinGroup", JoinGroupResponseAbstractPacket.class),
    QUITE_GROUP_REQUEST((byte)13, "quiteGroup", QuiteGroupRequestAbstractPacket.class),
    QUITE_GROUP_RESPONSE((byte)14, "quiteGroup", QuiteGroupResponseAbstractPacket.class),
    GROUP_MESSAGE_REQUEST((byte)15, "sendToGroup",GroupMessageRequestAbstractPacket.class),
    GROUP_MESSAGE_RESPONSE((byte)16, "sendToGroup", GroupMessageResponseAbstractPacket.class),
    HEART_BEAT_REQUEST((byte)17, "heartBeat", HeartBeatRequestAbstractPacket.class),
    HEART_BEAT_RESPONSE((byte)18, "heartBeat", HeartBeatResponseAbstractPacket.class),
    ADD_FRIEND_REQUEST((byte)19, "addFriend", AddFriendRequestAbstractPacket.class),
    ADD_FRIEND_RESPONSE((byte)20, "addFriend", AddFriendResponseAbstractPacket.class),
    DELETE_FRIEND_REQUEST((byte)21, "deleteFriend", DeleteFriendRequestAbstractPacket.class),
    DELETE_FRIEND_RESPONSE((byte)22, "deleteFriend", DeleteFriendResponseAbstractPacket.class),
    LIST_FRIENDS_REQUEST((byte)23, "listFriends", ListFriendsRequestAbstractPacket.class),
    LIST_FRIENDS_RESPONSE((byte)24, "listFriends", ListFriendsResponseAbstractPacket.class);

    private byte code;
    private String name;
    private Class<? extends AbstractPacket> type;

    Command(byte code, String name, Class<? extends AbstractPacket> type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Class<? extends AbstractPacket> getType() {
        return type;
    }
}
