package com.cooker.msgbus.common;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午10:34
 * 功能描述:
 * 修改历史:
 */
public enum  ChannelState {
    /** 未初始化 **/
    UNINIT(0),
    /** 已初始化 **/
    INIT(1),
    /** 存活可用状态 **/
    ALIVE(2),
    /** 不可用状态 **/
    UNALIVE(3),
    /** 关闭状态 **/
    CLOSE(4);
    public final int value;

    private ChannelState(int value){
        this.value = value;
    }

    public boolean isAliveState() {
        return this == ALIVE;
    }

    public boolean isUnAliveState() {
        return this == UNALIVE;
    }

    public boolean isCloseState() {
        return this == CLOSE;
    }

    public boolean isInitState() {
        return this == INIT;
    }

    public boolean isUnInitState() {
        return this == UNINIT;
    }
}
