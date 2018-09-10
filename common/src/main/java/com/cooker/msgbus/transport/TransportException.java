package com.cooker.msgbus.transport;

import java.io.IOException;
import java.net.InetSocketAddress;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 下午4:45
 * 功能描述:
 * 修改历史:
 */
public class TransportException extends IOException {

    private static final long serialVersionUID = 3021065849664455414L;

    private InetSocketAddress localAddress;
    private InetSocketAddress remoteAddress;

    public TransportException(InetSocketAddress localAddress, InetSocketAddress remoteAddress, String message) {
        super(message);
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public TransportException(InetSocketAddress localAddress, InetSocketAddress remoteAddress, String message, Throwable cause) {
        super(message, cause);
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }
}
