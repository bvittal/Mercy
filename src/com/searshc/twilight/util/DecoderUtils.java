package com.searshc.twilight.util;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import com.searshc.twilight.segments.Segment2AA7LengthCalculator;
import com.searshc.twilight.service.TwilightConstants;
import com.starmount.ups.sears.responses.segment2AB7.Segment2AB7LengthCalculator;
import com.starmount.ups.sears.responses.segment40BA.Segment40BALengthCalculator;
import com.starmount.ups.sears.responses.segment58B1.Segment58B1LengthCalculator;
import com.starmount.ups.sears.responses.segment60B1.Segment60B1LengthCalculator;
import com.starmount.ups.sears.responses.segment62B1.Segment62B1LengthCalculator;
import com.starmount.ups.sears.responses.segment95.Segment95LengthCalculator;
import com.starmount.ups.sears.responses.segment98.Segment98LengthCalculator;
import com.starmount.ups.sears.responses.segment9C.Segment9CLengthCalculator;
import com.starmount.ups.sears.responses.segmentE3.SegmentE3LengthCalculator;
import com.starmount.ups.sears.responses.segmentE8.SegmentE8LengthCalculator;
import com.starmount.ups.sears.responses.segmentE9.SegmentE9LengthCalculator;
import com.starmount.ups.sears.responses.segmentEA.SegmentEALengthCalculator;
import com.starmount.ups.sears.responses.segmentEC.SegmentECLengthCalculator;

public class DecoderUtils
{
  public static byte[] covertBigDecimalToBytes(String val){
    BigDecimal test = new BigDecimal(val);
    BigInteger theInt = test.unscaledValue();
    byte[] arr = theInt.toByteArray();
    return arr;
  }
  
  public static boolean lengthMatch(String indicator, StringBuilder builder){
    int segmentlength = 0;
    if(builder != null){
      byte buffer[] = buildResponse(builder);
      if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_E3)){
        SegmentE3LengthCalculator calc = new SegmentE3LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_E8)){
        SegmentE8LengthCalculator calc = new SegmentE8LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_E9)){
        SegmentE9LengthCalculator calc = new SegmentE9LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_EC)){
        SegmentECLengthCalculator calc = new SegmentECLengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_9C)){
        Segment9CLengthCalculator calc = new Segment9CLengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_95)){
        Segment95LengthCalculator calc = new Segment95LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_98)){
        Segment98LengthCalculator calc = new Segment98LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_EA)){
        SegmentEALengthCalculator calc = new SegmentEALengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_40BA)){
        Segment40BALengthCalculator calc = new Segment40BALengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_58B1)){
        Segment58B1LengthCalculator calc = new Segment58B1LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_60B1)){
        Segment60B1LengthCalculator calc = new Segment60B1LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_62B1)){
        Segment62B1LengthCalculator calc = new Segment62B1LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_2AB7)){
        Segment2AB7LengthCalculator calc = new Segment2AB7LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
      else if(indicator.equalsIgnoreCase(TwilightConstants.INDICATOR_2AA7)){
        Segment2AA7LengthCalculator calc = new Segment2AA7LengthCalculator(indicator, ByteBuffer.wrap(buffer));
        segmentlength = calc.getLength();
        if(buffer.length == segmentlength)
          return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }
  
  public static byte[] buildResponse(StringBuilder sb) {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    for (int i = 0; i < sb.length(); i += 3) {
        String s = sb.substring(i, i + 2);
        int unsignedByte = Integer.parseInt(s, 16);
        os.write(unsignedByte);
    }
    return os.toByteArray();
  }
}

