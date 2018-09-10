package com.cooker.msgbus.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/07 下午4:27
 * 功能描述:
 * 修改历史:
 */
public class NettyEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] bytes, ByteBuf out) throws Exception {
        out.writeBytes(bytes);
    }
}
