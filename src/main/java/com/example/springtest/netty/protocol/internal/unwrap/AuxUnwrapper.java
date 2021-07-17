package com.example.springtest.netty.protocol.internal.unwrap;

import com.example.springtest.netty.protocol.CodecConfig;
import com.example.springtest.netty.protocol.internal.ConfigParse.FieldTypeParam;
import com.example.springtest.netty.protocol.internal.ProtocalTemplate;
import com.example.springtest.netty.protocol.internal.fieldType.BcdUnsigned;
import com.example.springtest.netty.protocol.internal.packetSegment.Auxiliary;
import com.example.springtest.netty.protocol.internal.packetSegment.Control;
import com.example.springtest.netty.protocol.internal.packetSegment.PacketSegmentContext;
import com.example.springtest.netty.protocol.internal.packetSegment.SegmentEnum;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by PETER on 2015/3/25.
 */
public class AuxUnwrapper extends Unwrapper{
    private Calendar calendar=new GregorianCalendar();
    private BcdUnsigned bcdUnsigned=new BcdUnsigned();
    private FieldTypeParam timeField=new FieldTypeParam(1);
    @Override
    public void decode(ByteBuffer in, PacketSegmentContext packetSegmentContext,
                       ProtocalTemplate protocalTemplate, CodecConfig codecConfig) throws Exception{
        Control control=(Control)packetSegmentContext.getSegment(SegmentEnum.control);
        Auxiliary auxiliary=(Auxiliary)packetSegmentContext.getSegment(SegmentEnum.auxiliary);
        if(control.getFcbOrAcd()==1){
            auxiliary.setEc1(in.get());
            auxiliary.setEc2(in.get());
        }
        if(control.getTpV()==1){
            auxiliary.setPfc(in.get());
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.SECOND,Integer.parseInt(bcdUnsigned.decode(in,timeField)));
            calendar.set(Calendar.MINUTE,Integer.parseInt(bcdUnsigned.decode(in,timeField)));
            calendar.set(Calendar.HOUR,Integer.parseInt(bcdUnsigned.decode(in,timeField)));
            calendar.set(Calendar.DATE,Integer.parseInt(bcdUnsigned.decode(in,timeField)));
            auxiliary.setSendTime(calendar.getTimeInMillis());
            auxiliary.setTimeout(in.get());
        }

        if(next!=null){
            next.decode(in, packetSegmentContext, protocalTemplate, codecConfig);
        }
    }

}
