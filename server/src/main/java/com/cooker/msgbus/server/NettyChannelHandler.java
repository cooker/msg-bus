package com.cooker.msgbus.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/07 下午4:46
 * 功能描述:
 * 修改历史:
 */
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    private NettyServer nettyServer;
    private NettyServerChannelManager serverChannelManager;

    public NettyChannelHandler(NettyServer nettyServer, NettyServerChannelManager serverChannelManager) {
        this.nettyServer = nettyServer;
        this.serverChannelManager = serverChannelManager;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
