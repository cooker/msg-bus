package com.cooker.msgbus.rpc;/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/10 下午9:56
 * 功能描述:
 * <pre>
 *      1. 协议规约：[16-服务名]-[8-消息类型]-[8-主版本[0-f]]-[8-次版本[0-f]]
 * </pre>
 * 修改历史:
 */

public interface Protocol {
    short HEADER = 0x5955;
}
