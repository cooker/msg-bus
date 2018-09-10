package com.cooker.msgbus.codec;/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 上午10:17
 * 功能描述: 序列化
 * 修改历史:
 */

import java.io.IOException;

public interface Serialization {
    byte[] serialize(Object obj) throws IOException;
    byte[] serializeMulti(Object[] objs) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> cl) throws IOException;
    <T> Object[] deserializeMulti(byte[] bytes, Class<T> cl) throws IOException;

    /**
     * serialization的唯一编号，用于传输协议中指定序列化方式
     * @return 返回值 0-31
     */
    int getSerializationNumber();
}
