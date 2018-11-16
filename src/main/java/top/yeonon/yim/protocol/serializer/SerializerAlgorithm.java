package top.yeonon.yim.protocol.serializer;

/**
 * 序列化算法
 * @Author yeonon
 * @date 2018/11/15 0015 18:02
 **/
public enum SerializerAlgorithm {

    JSON((byte)1, "JSON");

    private byte code;
    private String name;

    SerializerAlgorithm(byte code, String name) {
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
