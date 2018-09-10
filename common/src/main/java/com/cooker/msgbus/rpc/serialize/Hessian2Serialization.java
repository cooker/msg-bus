package com.cooker.msgbus.rpc.serialize;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.cooker.msgbus.codec.Serialization;
import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 上午10:44
 * 功能描述:
 * 修改历史:
 */
public class Hessian2Serialization implements Serialization {

    @Override
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);
        out.writeObject(obj);
        out.flush();
        return bos.toByteArray();
    }

    @Override
    public byte[] serializeMulti(Object[] objs) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);
        for (Object obj : objs){
            out.writeObject(obj);
        }
        out.flush();
        return bos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> cl) throws IOException {
        Hessian2Input in = new Hessian2Input(new ByteArrayInputStream(bytes));
        return (T) in.readObject(cl);
    }

    @Override
    public <T> Object[] deserializeMulti(byte[] bytes, Class<T> cl) throws IOException {
        List<T> datas = Lists.newArrayList();
        Hessian2Input in = new Hessian2Input(new ByteArrayInputStream(bytes));
        while (!in.isEnd()){
            datas.add((T) in.readObject(cl));
        }
        return datas.toArray();
    }

    @Override
    public int getSerializationNumber() {
        return 0;
    }
}
