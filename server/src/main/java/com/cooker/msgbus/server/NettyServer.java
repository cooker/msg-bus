package com.cooker.msgbus.server;

import com.cooker.msgbus.common.ChannelState;
import com.cooker.msgbus.common.URLParamType;
import com.cooker.msgbus.rpc.Request;
import com.cooker.msgbus.rpc.Response;
import com.cooker.msgbus.rpc.URL;
import com.cooker.msgbus.transport.AbstractServer;
import com.cooker.msgbus.transport.TransportException;
import com.cooker.msgbus.util.LoggerUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.Objects;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/04 下午3:02
 * 功能描述: 服务端
 * 修改历史:
 */
public class NettyServer extends AbstractServer {
    protected NettyServerChannelManager channelManager;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;

    public NettyServer(URL url){
        super(url);
    }

    @Override
    public boolean isBound() {
        return serverChannel != null && serverChannel.isActive();
    }

    @Override
    public synchronized boolean open() {
        if(isAvailable()){
            LoggerUtil.warn("NettyServer serverChannel already open >> url={}", url);
            return state.isAliveState();
        }
        if(Objects.isNull(bossGroup)){
            int cpu = 1;
            if(!url.hasParam(URLParamType.bossGroup.getName())){
                cpu = Runtime.getRuntime().availableProcessors();

            }else{
               cpu = url.getInt(URLParamType.bossGroup.getName(), URLParamType.bossGroup.getIntValue());
            }
            bossGroup = new NioEventLoopGroup(cpu);
            workerGroup = new NioEventLoopGroup(cpu << 2);
        }
        int maxServerConnection = url.getInt(URLParamType.maxServerConnection.getName(), URLParamType.maxServerConnection.getIntValue());
        int maxContentLength = url.getInt(URLParamType.maxContentLength.getName(), URLParamType.maxContentLength.getIntValue());
        channelManager = new NettyServerChannelManager(maxServerConnection);
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  protected void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline()
                        .addLast("channelManager", channelManager)
                        .addLast("decoder", new NettyDecoder(maxContentLength))
                        .addLast("encoder", new NettyEncoder())
                        .addLast("channelHandler", new NettyChannelHandler(NettyServer.this, channelManager));
                  }
              });
        server.childOption(ChannelOption.TCP_NODELAY, true)
              .childOption(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture future = server.bind(new InetSocketAddress(url.getPort()));
        future.syncUninterruptibly();
        serverChannel = future.channel();
        state = ChannelState.ALIVE;
        LoggerUtil.info("NettyServer server start finish：url={}", url);
        return state.isAliveState();
    }

    @Override
    public synchronized void close() {
        close(0);
    }

    @Override
    public synchronized void close(int timeout) {
        if(state.isCloseState()){
            LoggerUtil.info("NettyServer close fail: already close, url={}", url);
            return;
        }

        if(state.isUnInitState()){
            LoggerUtil.info("NettyServer close fail: don't close this node beacuse state is uninit url={}", url);
            return;
        }

        try {
            //close listen
            if(serverChannel != null){
                serverChannel.close();
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
                bossGroup = null;
                workerGroup = null;
            }
            //close all client's channel
            if(channelManager != null){
                channelManager.close();
            }

            state = ChannelState.CLOSE;
        }catch (Exception e){
            LoggerUtil.error("NettyServer close() error", e);
        }
    }

    @Override
    public boolean isClosed() {
        return state.isCloseState();
    }

    @Override
    public boolean isAvailable() {
        return state.isAliveState();
    }

    @Override
    public Response request(Request request) throws TransportException {
        LoggerUtil.error("NettyServer request(Request request) method not support");
        return null;
    }
}
