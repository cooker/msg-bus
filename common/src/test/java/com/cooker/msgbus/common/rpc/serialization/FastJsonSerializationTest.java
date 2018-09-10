package com.cooker.msgbus.common.rpc.serialization;

import com.cooker.msgbus.rpc.serialize.FastJsonSerialization;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 下午2:20
 * 功能描述:
 * 修改历史:
 */
public class FastJsonSerializationTest {

    FastJsonSerialization serialization;

    @Before
    public void init(){
        serialization = new FastJsonSerialization();
    }

    @Test
    public void toBytes2() throws IOException {
        byte[] bytes;
        bytes = serialization.serialize(Integer.valueOf(1));
        System.out.println("数值测试：" + Base64.encodeBase64String(bytes));

        bytes = serialization.serialize("我的啊");
        System.out.println("字符串测试：" + Base64.encodeBase64String(bytes));

        TestBeans.A a = new TestBeans.A();
        a.setA(1);
        a.setB("我大");
        bytes = serialization.serialize(a);
        System.out.println("对象测试：" + Base64.encodeBase64String(bytes));
    }

    @Test
    public void toBytes(){

    }

    @Test
    public void toObject() throws IOException {
//        数值测试：MQ==
//        字符串测试：IuaIkeeahOWViiI=
//        对象测试：eyJhIjoxLCJiIjoi5oiR5aSnIn0=
        byte[] bytes = Base64.decodeBase64("eyJhIjoxLCJiIjoi5oiR5aSnIn0=");
        System.out.println(serialization.deserialize(bytes, TestBeans.A.class));
    }

    @Test
    public void toObjects() throws IOException {
        byte[] bytes = serialization.serializeMulti(new Object[]{1, 99, 76});
        Object[] strs = serialization.deserializeMulti(bytes, String.class);
        System.out.println(Arrays.asList(strs).get(0).getClass());
    }

}
