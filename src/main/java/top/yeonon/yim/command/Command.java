package top.yeonon.yim.command;

import top.yeonon.yim.protocol.packet.Packet;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendRequestPacket;
import top.yeonon.yim.protocol.packet.addFriend.AddFriendResponsePacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupRequestPacket;
import top.yeonon.yim.protocol.packet.createGroup.CreateGroupResponsePacket;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendRequestPacket;
import top.yeonon.yim.protocol.packet.deleteFriend.DeleteFriendResponsePacket;
import top.yeonon.yim.protocol.packet.exception.ExceptionPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageRequestPacket;
import top.yeonon.yim.protocol.packet.groupMessage.GroupMessageResponsePacket;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatRequestPacket;
import top.yeonon.yim.protocol.packet.heartBeat.HeartBeatResponsePacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupRequestPacket;
import top.yeonon.yim.protocol.packet.joinGroup.JoinGroupResponsePacket;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsRequestPacket;
import top.yeonon.yim.protocol.packet.listFriend.ListFriendsResponsePacket;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberRequestPacket;
import top.yeonon.yim.protocol.packet.listGroup.ListGroupMemberResponsePacket;
import top.yeonon.yim.protocol.packet.login.LoginRequestPacket;
import top.yeonon.yim.protocol.packet.login.LoginResponsePacket;
import top.yeonon.yim.protocol.packet.logout.LogoutRequestPacket;
import top.yeonon.yim.protocol.packet.logout.LogoutResponsePacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupRequestPacket;
import top.yeonon.yim.protocol.packet.quiteGroup.QuiteGroupResponsePacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageRequestPacket;
import top.yeonon.yim.protocol.packet.singleMessage.SingleMessageResponsePacket;

/**
 * 命令枚举类，枚举实例代表了各个命令的代码以及名称
 *
 * @Author yeonon
 * @date 2018/11/15 0015 18:00
 **/
public enum Command {

    //0号表示异常
    EXCEPTION((byte)0, "exception", ExceptionPacket.class),

    LOGIN_REQUEST((byte) 1, "login", LoginRequestPacket.class),
    LOGIN_RESPONSE((byte)2, "login", LoginResponsePacket.class),
    LOGOUT_REQUEST((byte)3, "logout", LogoutRequestPacket.class),
    LOGOUT_RESPONSE((byte)4, "logout", LogoutResponsePacket.class),
    SINGLE_MESSAGE_REQUEST((byte)5, "sendToUser", SingleMessageRequestPacket.class),
    SINGLE_MESSAGE_RESPONSE((byte)6, "sendToUser", SingleMessageResponsePacket.class),
    CREATE_GROUP_REQUEST((byte)7, "createGroup", CreateGroupRequestPacket.class),
    CREATE_GROUP_RESPONSE((byte)8, "createGroup", CreateGroupResponsePacket.class),
    LIST_GROUP_MEMBER_REQUEST((byte)9, "listGroup", ListGroupMemberRequestPacket.class),
    LIST_GROUP_MEMBER_RESPONSE((byte)10, "listGroup", ListGroupMemberResponsePacket.class),
    JOIN_GROUP_REQUEST((byte)11, "joinGroup", JoinGroupRequestPacket.class),
    JOIN_GROUP_RESPONSE((byte)12, "joinGroup", JoinGroupResponsePacket.class),
    QUITE_GROUP_REQUEST((byte)13, "quiteGroup", QuiteGroupRequestPacket.class),
    QUITE_GROUP_RESPONSE((byte)14, "quiteGroup", QuiteGroupResponsePacket.class),
    GROUP_MESSAGE_REQUEST((byte)15, "sendToGroup",GroupMessageRequestPacket.class),
    GROUP_MESSAGE_RESPONSE((byte)16, "sendToGroup", GroupMessageResponsePacket.class),
    HEART_BEAT_REQUEST((byte)17, "heartBeat", HeartBeatRequestPacket.class),
    HEART_BEAT_RESPONSE((byte)18, "heartBeat", HeartBeatResponsePacket.class),
    ADD_FRIEND_REQUEST((byte)19, "addFriend", AddFriendRequestPacket.class),
    ADD_FRIEND_RESPONSE((byte)20, "addFriend", AddFriendResponsePacket.class),
    DELETE_FRIEND_REQUEST((byte)21, "deleteFriend", DeleteFriendRequestPacket.class),
    DELETE_FRIEND_RESPONSE((byte)22, "deleteFriend", DeleteFriendResponsePacket.class),
    LIST_FRIENDS_REQUEST((byte)23, "listFriends", ListFriendsRequestPacket.class),
    LIST_FRIENDS_RESPONSE((byte)24, "listFriends", ListFriendsResponsePacket.class);

    private byte code;
    private String name;
    private Class<? extends Packet> type;

    Command(byte code, String name, Class<? extends Packet> type) {
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

    public Class<? extends Packet> getType() {
        return type;
    }
}
