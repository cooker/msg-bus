package com.cooker.msgbus.common;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 下午5:31
 * 功能描述:
 * 修改历史:
 */
public enum URLParamType {
    host("host", "localhost"),
    port("port", -1),
    group("group", "default_rpc_group"),
    nodeType("nodeType", Contants.NODE_TYPE_SERVICE),
    version("version", Contants.DEFAULT_VERSION),
    bossGroup("bossGroup", 1),
    //客户端最大连接数
    maxServerConnection("maxServerConnection", 100000),
    //最大消息请求（bit）
    maxContentLength("maxContentLength", 10 * 1024 * 1024);


    private String name;
    private String value;
    private boolean boolValue;
    private long longValue;
    private int intValue;
    private double doubleValue;

    URLParamType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    URLParamType(String name, boolean boolValue) {
        this.name = name;
        this.boolValue = boolValue;
    }

    URLParamType(String name, long longValue) {
        this.name = name;
        this.longValue = longValue;
    }

    URLParamType(String name, int intValue) {
        this.name = name;
        this.intValue = intValue;
    }

    URLParamType(String name, double doubleValue) {
        this.name = name;
        this.doubleValue = doubleValue;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }
}
