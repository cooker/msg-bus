package com.cooker.msgbus.transport;/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午9:42
 * 功能描述:
 * 修改历史:
 */

import java.net.InetSocketAddress;
import java.util.Collection;

public interface Server extends Endpoint {

    Collection<Channel> getChannels();

    Collection<Channel> getChannel(InetSocketAddress remoteAddress);

    boolean isBound();
}
