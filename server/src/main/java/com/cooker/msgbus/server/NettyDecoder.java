package com.cooker.msgbus.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/07 下午4:27
 * 功能描述:
 * 修改历史:
 */
public class NettyDecoder extends ByteToMessageDecoder {

    private int maxContentLength = 0;

    public NettyDecoder(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

    }
}
