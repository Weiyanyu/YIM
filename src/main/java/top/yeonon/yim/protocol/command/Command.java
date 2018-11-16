package top.yeonon.yim.protocol.command;

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
    SINGLE_MESSAGE_RESPONSE((byte)6, "sendToUser");

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
