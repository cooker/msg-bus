package com.cooker.msgbus.transport;/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午10:02
 * 功能描述:
 * 修改历史:
 */

import com.cooker.msgbus.rpc.Request;
import com.cooker.msgbus.rpc.Response;

import java.net.InetSocketAddress;

public interface Channel {
    boolean open();

    void close();

    void close(int timeout);

    boolean isClosed();

    InetSocketAddress getLoadAdress();

    InetSocketAddress getRemoteAddress();

    boolean isAvailable();

    /**
     * send request
     * @param request
     * @return
     */
    Response request(Request request) throws TransportException;
}
