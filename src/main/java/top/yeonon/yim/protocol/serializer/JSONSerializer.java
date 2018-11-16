package top.yeonon.yim.protocol.serializer;

import com.alibaba.fastjson.JSON;

/**
 *
 * 基于JSON的序列化，使用的是 alibaba fastjson
 * @Author yeonon
 * @date 2018/11/15 0015 18:05
 **/
public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<? extends T> clz, byte[] bytes) {
        return JSON.parseObject(bytes, clz);
    }
}
