package com.example.springtest.netty.protocol.resolver;

import com.example.springtest.netty.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author admin
 * @date 2021-07-03 18:11
 */
public interface IMessageResolver {
    /**
     * message resolve
     * @param packet
     */
    void resolve(ChannelHandlerContext ctx, Packet packet) throws Exception;
}
