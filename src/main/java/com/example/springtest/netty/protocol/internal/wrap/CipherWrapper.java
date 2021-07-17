package com.example.springtest.netty.protocol.internal.wrap;

import com.example.springtest.netty.protocol.CodecConfig;
import com.example.springtest.netty.protocol.Packet;
import com.example.springtest.netty.protocol.internal.ProtocalTemplate;
import com.example.springtest.netty.protocol.internal.packetSegment.Control;
import com.example.springtest.netty.protocol.internal.packetSegment.Data;
import com.example.springtest.netty.protocol.internal.packetSegment.PacketSegmentContext;
import com.example.springtest.netty.protocol.internal.packetSegment.SegmentEnum;

import java.util.List;

/**
 * Created by PETER on 2015/5/5.
 */
public class CipherWrapper extends Wrapper{

    @Override
    void encode(Packet in, PacketSegmentContext packetSegmentContext, ProtocalTemplate protocalTemplate, CodecConfig codecConfig) throws Exception {
        Control control=(Control)packetSegmentContext.getSegment(SegmentEnum.control);
        Data dataSeg=(Data)packetSegmentContext.getSegment(SegmentEnum.data);
        int afn=control.getAfn();

        if(afn==0x04 || afn==0x05){
            List<byte[]> body=dataSeg.getBuffer();
            //加密
            List<byte[]> crypted=body;
            dataSeg.getBuffer().clear();
            dataSeg.getBuffer().addAll(crypted);
        }
        if(next!=null){
            next.encode(in,packetSegmentContext,protocalTemplate,codecConfig);
        }
    }
}
