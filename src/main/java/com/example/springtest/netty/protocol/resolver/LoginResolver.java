package com.example.springtest.netty.protocol.resolver;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HexUtil;
import com.example.springtest.netty.TcpHelper;
import com.example.springtest.netty.protocol.BinPacket;
import com.example.springtest.netty.protocol.Packet;
import com.example.springtest.netty.protocol.ProtocalManagerHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * device login packet resolver
 * @author admin
 * @date 2021-07-03 18:20
 */
@Slf4j
public class LoginResolver implements IMessageResolver{
    @Override
    public void resolve(ChannelHandlerContext ctx, Packet packet) throws Exception {
        Packet rgmPacket=new Packet();
        rgmPacket.setCommand("Confirm");
        rgmPacket.setTerminalAddress(packet.getTerminalAddress());
        rgmPacket.setLine(0);
        rgmPacket.setData(new HashMap<>(0));
        BinPacket binPacket = new BinPacket();
        ProtocalManagerHelper.getProtocalManagerRgm().encode(rgmPacket,binPacket);
        TcpHelper.sendMessage(ctx,binPacket);
    }
}
