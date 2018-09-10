package com.cooker.msgbus.common;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/10 下午11:46
 * 功能描述: 版本控制
 * 修改历史:
 */
public class Version {
    byte mversion;
    byte bversion;

    public Version(byte mversion, byte bversion) {
        this.mversion = mversion;
        this.bversion = bversion;
    }

    @Override
    public String toString() {
        return String.format("%d.%d", mversion, bversion);
    }
}
