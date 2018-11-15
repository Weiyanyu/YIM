package top.yeonon.yim.protocol.serializer;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 18:02
 **/
public interface Serializer {


    Serializer DEFAULT_SERIALIZER = new JSONSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<? extends T> clz, byte[] bytes);
}
