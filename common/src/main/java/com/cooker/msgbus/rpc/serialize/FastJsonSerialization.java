package com.cooker.msgbus.rpc.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cooker.msgbus.codec.Serialization;
import java.io.*;
import java.util.List;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 上午10:46
 * 功能描述:
 * 修改历史:
 */
public class FastJsonSerialization implements Serialization {

    @Override
    public byte[] serialize(Object data) throws IOException {
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.WriteEnumUsingToString, true);
        serializer.config(SerializerFeature.WriteClassName, true);
        serializer.write(data);
        return out.toBytes("utf-8");
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) throws IOException {
        return JSON.parseObject(new String(data), clz);
    }

    @Override
    public byte[] serializeMulti(Object[] data) throws IOException {
        return serialize(data);
    }

    @Override
    public <T> Object[] deserializeMulti(byte[] bytes, Class<T> cl) throws IOException {
        List<T> list = JSON.parseArray(new String(bytes), cl);
        if (list != null) {
            return list.toArray();
        }
        return null;
    }

    @Override
    public int getSerializationNumber() {
        return 1;
    }
}
