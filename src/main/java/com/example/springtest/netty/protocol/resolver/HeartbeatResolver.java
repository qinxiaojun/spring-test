package com.example.springtest.netty.protocol.resolver;

import com.example.springtest.netty.TcpHelper;
import com.example.springtest.netty.protocol.BinPacket;
import com.example.springtest.netty.protocol.Packet;
import com.example.springtest.netty.protocol.ProtocalManagerHelper;
import io.netty.channel.ChannelHandlerContext;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2021-07-05 15:46
 */
public class HeartbeatResolver implements IMessageResolver{

    @Override
    public void resolve(ChannelHandlerContext ctx, Packet packet) throws Exception {
        Packet rgmPacket=new Packet();
        rgmPacket.setCommand("Confirm");
        rgmPacket.setTerminalAddress(packet.getTerminalAddress());
        rgmPacket.setLine(0);

    /*    Map<String, Object> data = new HashMap<String, Object>();
        Calendar calendar = Calendar.getInstance();
        Map<String, Object> timeData = new HashMap<String, Object>();
        timeData.put("second", calendar.get(Calendar.SECOND));
        timeData.put("minute", calendar.get(Calendar.MINUTE));
        timeData.put("hour", calendar.get(Calendar.HOUR_OF_DAY));
        timeData.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        timeData.put("month", calendar.get(Calendar.MONTH) + 1);
        timeData.put("week", calendar.get(Calendar.DAY_OF_WEEK));
        timeData.put("year", calendar.get(Calendar.YEAR));
        data.put("terminalTime", timeData);
        rgmPacket.setData(data);*/
        BinPacket binPacket = new BinPacket();
        ProtocalManagerHelper.getProtocalManagerRgm().encode(rgmPacket,binPacket);
        TcpHelper.sendMessage(ctx,binPacket);
    }
}
