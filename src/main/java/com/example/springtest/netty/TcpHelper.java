package com.example.springtest.netty;

import cn.hutool.core.util.HexUtil;
import com.example.springtest.netty.protocol.BinPacket;
import com.example.springtest.netty.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qinxj
 * @date 2020/11/6 14:52
 */
@Slf4j
public class TcpHelper {

    public static void sendMessage(ChannelHandlerContext ctx,String message){
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(HexUtil.decodeHex(message));
        ctx.writeAndFlush(buffer);
        log.info("send message - {} to {}",message,ctx.channel().remoteAddress());
    }

    public static void sendMessage(ChannelHandlerContext ctx, BinPacket packet){
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(packet.getByteBuffer().array());
        ctx.writeAndFlush(buffer);
        log.info("send message - {} to {}",HexUtil.encodeHexStr(packet.getByteBuffer().array()),ctx.channel().remoteAddress());
    }
}
