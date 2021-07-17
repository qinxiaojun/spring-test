package com.example.springtest.netty;

import cn.hutool.core.util.HexUtil;
import com.example.springtest.SpringBeanUtil;
import com.example.springtest.netty.protocol.ChannelUtil;
import com.example.springtest.netty.protocol.Packet;
import com.example.springtest.netty.protocol.ProtocalManagerHelper;
import com.example.springtest.netty.protocol.resolver.MessageResolverFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * @author qinxj
 * @date 2020/11/6 12:57
 */
@Slf4j
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        byte[] bytes =new byte[in.readableBytes()];
        in.readBytes(bytes);
        in.release();
        Packet rgmPacket=new Packet();
        ProtocalManagerHelper.getProtocalManagerRgm().decode(ByteBuffer.wrap(bytes),rgmPacket);

        log.info("receive message - {} from {}",HexUtil.encodeHexStr(bytes), ctx.channel().remoteAddress());
        log.info("packet - {} ",rgmPacket);
        MessageResolverFactory.getMessageResolver(rgmPacket.getCommand()).resolve(ctx,rgmPacket);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("client - {} connected", socketAddress);
        ChannelUtil.put(socketAddress.getHostString(),ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("client - {} disconnected", socketAddress);
        ChannelUtil.remove(socketAddress.getHostString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("tcp server caught exception - {} ",cause.toString());
    }
}
