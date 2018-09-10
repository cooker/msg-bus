package com.cooker.msgbus.server;

import com.cooker.msgbus.common.ChannelState;
import com.cooker.msgbus.rpc.Request;
import com.cooker.msgbus.rpc.Response;
import com.cooker.msgbus.transport.Channel;
import com.cooker.msgbus.transport.TransportException;
import com.cooker.msgbus.util.LoggerUtil;

import java.net.InetSocketAddress;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午10:19
 * 功能描述:
 * 修改历史:
 */
public class NettyChannel implements Channel {
    private InetSocketAddress localAddress = null;
    private InetSocketAddress remoteAddress = null;
    private io.netty.channel.Channel channel = null;
    private NettyChannel nettyChannel;
    private volatile ChannelState state = ChannelState.UNINIT;

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public void close() {
        close(0);
    }

    @Override
    public void close(int timeout) {
        try {
            state = ChannelState.CLOSE;
            if(channel != null){
                channel.close();
            }
        }catch (Exception e){
            LoggerUtil.error("NettyChannel close Error：local={} remote={}",
                    localAddress, remoteAddress, e);
        }
    }

    @Override
    public boolean isClosed() {
        return state.isCloseState();
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
    public boolean isAvailable() {
        return state.isAliveState();
    }

    @Override
    public Response request(Request request) throws TransportException {

        return null;
    }


}
