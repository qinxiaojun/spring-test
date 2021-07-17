package com.example.springtest.netty.protocol.internal.wrap;


import com.example.springtest.netty.protocol.CodecConfig;
import com.example.springtest.netty.protocol.Packet;
import com.example.springtest.netty.protocol.internal.ProtocalTemplate;
import com.example.springtest.netty.protocol.internal.packetSegment.PacketSegmentContext;

/**
 * Created by PETER on 2015/3/24.
 */
public abstract class Wrapper {
    protected Wrapper next;
    abstract void encode(Packet in, PacketSegmentContext packetSegmentContext,
                         ProtocalTemplate protocalTemplate, CodecConfig codecConfig) throws Exception;

    public void setNext(Wrapper next) {
        this.next = next;
    }
}
