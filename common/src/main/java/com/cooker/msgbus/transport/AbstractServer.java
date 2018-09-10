package com.cooker.msgbus.transport;

import com.cooker.msgbus.common.ChannelState;
import com.cooker.msgbus.rpc.URL;

import java.net.InetSocketAddress;
import java.util.Collection;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午9:41
 * 功能描述:
 * 修改历史:
 */
public abstract class AbstractServer implements Server {
    protected ChannelState state = ChannelState.UNINIT;
    protected InetSocketAddress localAddress;
    protected InetSocketAddress remoteAddress;
    protected URL url;

    public AbstractServer(){

    }

    public AbstractServer(URL url) {
        setUrl(url);
    }

    @Override
    public InetSocketAddress getLoadAdress() {
        return localAddress;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public Collection<Channel> getChannels() {
        return null;
    }

    @Override
    public Collection<Channel> getChannel(InetSocketAddress remoteAddress) {
        return null;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
