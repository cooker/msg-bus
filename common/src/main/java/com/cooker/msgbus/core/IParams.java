package com.cooker.msgbus.core;

import com.google.common.base.Preconditions;

import java.util.Optional;
import java.util.StringJoiner;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 下午4:48
 * 功能描述: 获取参数，类型适配
 * 修改历史:
 */
public interface IParams {

    default String getString(String key){
        return getString(key, null);
    }
    default int getInt(String key){
        return getInt(key, 0);
    }
    default long getLong(String key){
        return getLong(key, 0);
    }
    default boolean getBoolean(String key){
        return getBoolean(key, false);
    }
    default float getFloat(String key) {return getFloat(key, 0.0f);}
    default double getDouble(String key) {return getDouble(key, 0.0d);}
    //获取拆分的字符串
    default String[] getSplitStr(String delimiter, String key){
        Optional<String> str = getStr2Optional(key, "");
        if(str.isPresent()){
            return str.get().split(delimiter);
        }
        return new String[]{};
    }
    default String getMergeStr(String delimiter, String... keys){
        Preconditions.checkNotNull(keys);
        StringJoiner sj = new StringJoiner(delimiter);
        for (String key : keys){
            Optional<String> val = getStr2Optional(key, "");
            val.ifPresent(
                    (v)->{
                        sj.add(v);
                    }
            );
        }
        return sj.toString();
    }

    String getString(String key, String dVal);
    default Optional<String> getStr2Optional(String key, String dVal){
        return Optional.ofNullable(getString(key, dVal));
    }
    long getLong(String key, long dVal);
    int getInt(String key, int dVal);
    boolean getBoolean(String key, boolean dVal);
    float getFloat(String key, float dVal);
    double getDouble(String key, double dVal);
}
