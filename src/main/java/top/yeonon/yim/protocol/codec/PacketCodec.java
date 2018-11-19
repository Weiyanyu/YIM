package top.yeonon.yim.protocol.codec;

import io.netty.buffer.ByteBuf;
import top.yeonon.yim.command.Command;
import top.yeonon.yim.protocol.packet.Packet;
import top.yeonon.yim.protocol.serializer.JSONSerializer;
import top.yeonon.yim.protocol.serializer.Serializer;
import top.yeonon.yim.protocol.serializer.SerializerAlgorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心类
 *
 * 用于数据对象Packet的编解码，编解码遵循YIM自定义协议：
 *
 * -------- || -------- || -------- || -------- || -------- || --------
 * 魔数         版本号     序列化算法     指令        数据长度     数据
 * -------- || -------- || -------- || -------- || -------- || --------
 * 4字节        1字节       1字节         1字节       4字节       不定
 *
 * @Author yeonon
 * @date 2018/11/15 0015 18:33
 **/
public class PacketCodec {

    //魔数
    public static final int MAGIC_NUMBER = 0x15010622;

    //序列化器的Map
    private static final Map<Byte, Serializer> serializerMaps;

    //packet类型的Map
    private static final Map<Byte, Class<? extends Packet>> packetTypeMaps;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private PacketCodec() {}


    static {
        //初始化以及填充
        serializerMaps = new ConcurrentHashMap<>();
        serializerMaps.put(SerializerAlgorithm.JSON.getCode(), new JSONSerializer());

        //初始化以及填充
        packetTypeMaps = new ConcurrentHashMap<>();
        for (Command command : Command.values()) {
            packetTypeMaps.put(command.getCode(), command.getType());
        }
    }


    /**
     * 编码
     * @param buf 外部传进来的buf
     * @param packet 数据对象
     * @return 填满的buf
     */
    public ByteBuf encode(ByteBuf buf, Packet packet) {
        //填充魔数
        buf.writeInt(MAGIC_NUMBER);
        //版本号
        buf.writeByte(packet.getVersion());
        //序列化算法
        buf.writeByte(Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm());
        //指令
        buf.writeByte(packet.getCommand());
        //数据长度
        byte[] data = Serializer.DEFAULT_SERIALIZER.serialize(packet);
        int len = data.length;
        buf.writeInt(len);
        //数据
        buf.writeBytes(data);
        return buf;
    }

    /**
     * 解码
     * @param buf 装有数据的buf
     * @return 解码后的数据对象
     */
    public Packet decode(ByteBuf buf) {
        //跳过魔数
        buf.skipBytes(4);
        //跳过版本号
        buf.skipBytes(1);
        //拿到序列化算法
        byte serializerAlgorithm = buf.readByte();
        //拿到指令
        byte command = buf.readByte();
        //拿到数据长度和数据
        int len = buf.readInt();
        byte[] data = new byte[len];
        buf.readBytes(data);

        //根据序列化算法解析出具体的实例
        Serializer serializer = getSerializer(serializerAlgorithm);

        //根据指令，取得对应的实例类型
        Class<? extends Packet> packetType = getPacketType(command);

        //其中一个为null，都无法解码
        if (serializer == null || packetType == null) {
            return null;
        }
        //开始将数据反序列化成对象
        return serializer.deserialize(packetType, data);
    }

    /**
     * 获取序列化器实例
     * @param serializerAlgorithm 序列化算法
     * @return 序列化器
     */
    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMaps.get(serializerAlgorithm);
    }

    /**
     * 获取Packet数据对象的类型（反序列化的时候要用到）
     * @param command 指令类型
     * @return 类型
     */
    private Class<? extends Packet> getPacketType(byte command) {
        return packetTypeMaps.get(command);
    }
}
