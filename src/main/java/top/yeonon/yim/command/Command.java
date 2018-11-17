package top.yeonon.yim.command;

/**
 * 命令枚举类，枚举实例代表了各个命令的代码以及名称
 *
 * @Author yeonon
 * @date 2018/11/15 0015 18:00
 **/
public enum Command {

    LOGIN_REQUEST((byte) 1, "login"),
    LOGIN_RESPONSE((byte)2, "login"),
    LOGOUT_REQUEST((byte)3, "logout"),
    LOGOUT_RESPONSE((byte)4, "logout"),
    SINGLE_MESSAGE_REQUEST((byte)5, "sendToUser"),
    SINGLE_MESSAGE_RESPONSE((byte)6, "sendToUser"),
    CREATE_GROUP_REQUEST((byte)7, "createGroup"),
    CREATE_GROUP_RESPONSE((byte)8, "createGroup"),
    LIST_GROUP_MEMBER_REQUEST((byte)9, "listGroup"),
    LIST_GROUP_MEMBER_RESPONSE((byte)10, "listGroup"),
    JOIN_GROUP_REQUEST((byte)11, "joinGroup"),
    JOIN_GROUP_RESPONSE((byte)12, "joinGroup"),
    QUITE_GROUP_REQUEST((byte)13, "quiteGroup"),
    QUITE_GROUP_RESPONSE((byte)14, "quiteGroup"),
    GROUP_MESSAGE_REQUEST((byte)15, "sendToGroup"),
    GROUP_MESSAGE_RESPONSE((byte)16, "sendToGroup"),
    HEART_BEAT_REQUEST((byte)17, "heartBeat"),
    HEART_BEAT_RESPONSE((byte)18, "heartBeat");

    private byte code;
    private String name;

    Command(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
