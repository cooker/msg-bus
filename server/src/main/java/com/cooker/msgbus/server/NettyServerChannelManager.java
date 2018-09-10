package com.cooker.msgbus.server;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 上午10:18
 * 功能描述:
 * 修改历史:
 */

import com.cooker.msgbus.util.LoggerUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ChannelHandler.Sharable
public class NettyServerChannelManager extends ChannelInboundHandlerAdapter {
    private ConcurrentMap<String, Channel> channels = new ConcurrentHashMap<>();

    private int maxChannel;

    public NettyServerChannelManager(int maxChannel){
        super();
        this.maxChannel = maxChannel;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        int size = channels.size();
        if(size >= maxChannel){
            //超过最大连接数限制，直接close连接
            LoggerUtil.warn("NettyServerChannelManager channel connected size out of limit={} current={}", maxChannel, size);
            ch.close();
        }else{
            String key = getChannelKey(ch);
            channels.put(key, ch);
            ctx.fireChannelRegistered();
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        String key = getChannelKey(ch);
        channels.remove(key);
        ctx.fireChannelUnregistered();
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    public void close(){
        for(Map.Entry<String, Channel> entry : channels.entrySet()){
            try {
                Channel ch = entry.getValue();
                if(ch != null){
                    ch.close();
                }
            }catch (Exception e){
                LoggerUtil.error("NettyServerChannelManager close channel Error：{}", entry.getKey(), e);
            }
        }
    }

    private String getChannelKey(Channel ch){
        return getChannelKey((InetSocketAddress) ch.localAddress(), (InetSocketAddress) ch.remoteAddress());
    }

    /**
     * remote address + local address 作为连接的唯一标示
     *
     * @param local
     * @param remote
     * @return
     */
    private String getChannelKey(InetSocketAddress local, InetSocketAddress remote){
        String key = "";
        if (local == null || local.getAddress() == null){
            key += "null-";
        }else{
            key += local.getAddress().getHostAddress() + ":" + local.getPort() + "-";
        }

        if (remote == null || remote.getAddress() == null){
            key += "null-";
        }else{
            key += remote.getAddress().getHostAddress() + ":" + remote.getPort();
        }

        return key;
    }
}
