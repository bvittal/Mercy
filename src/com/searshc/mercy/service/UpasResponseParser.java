package com.searshc.mercy.service;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.SearsMessageParser;
import com.starmount.ups.sears.SearsResponseType;
import com.starmount.ups.sears.SegmentIndex;
import com.starmount.ups.sears.responses.segment11B6.ResponseSegment11B6;
import com.starmount.ups.sears.responses.segment11B6.Segment11B6LengthCalculator;
import com.starmount.ups.sears.responses.segment12B1.ResponseSegment12B1;
import com.starmount.ups.sears.responses.segment12B1.Segment12B1LengthCalculator;
import com.starmount.ups.sears.responses.segment2AB7.ResponseSegment2AB7;
import com.starmount.ups.sears.responses.segment2AB7.Segment2AB7LengthCalculator;
import com.starmount.ups.sears.responses.segment40BA.ResponseSegment40BA;
import com.starmount.ups.sears.responses.segment40BA.Segment40BALengthCalculator;
import com.starmount.ups.sears.responses.segment48B1.ResponseSegment48B1;
import com.starmount.ups.sears.responses.segment48B1.Segment48B1LengthCalculator;
import com.starmount.ups.sears.responses.segment58B1.ResponseSegment58B1;
import com.starmount.ups.sears.responses.segment58B1.Segment58B1LengthCalculator;
import com.starmount.ups.sears.responses.segment60B1.ResponseSegment60B1;
import com.starmount.ups.sears.responses.segment60B1.Segment60B1LengthCalculator;
import com.starmount.ups.sears.responses.segment62B1.ResponseSegment62B1;
import com.starmount.ups.sears.responses.segment62B1.Segment62B1LengthCalculator;
import com.starmount.ups.sears.responses.segment70B4.ResponseSegment70B4;
import com.starmount.ups.sears.responses.segment70B4.Segment70B4LengthCalculator;
import com.starmount.ups.sears.responses.segment72B2.ResponseSegment72B2;
import com.starmount.ups.sears.responses.segment72B2.Segment72B2LengthCalculator;
import com.starmount.ups.sears.responses.segment72B6.ResponseSegment72B6;
import com.starmount.ups.sears.responses.segment72B6.Segment72B6LengthCalculator;
import com.starmount.ups.sears.responses.segment72B7.ResponseSegment72B7;
import com.starmount.ups.sears.responses.segment72B7.Segment72B7LengthCalculator;
import com.starmount.ups.sears.responses.segment95.ResponseSegment95;
import com.starmount.ups.sears.responses.segment95.Segment95LengthCalculator;
import com.starmount.ups.sears.responses.segment98.ResponseSegment98;
import com.starmount.ups.sears.responses.segment98.Segment98LengthCalculator;
import com.starmount.ups.sears.responses.segment9C.ResponseSegment9C;
import com.starmount.ups.sears.responses.segment9C.Segment9CLengthCalculator;
import com.starmount.ups.sears.responses.segmentBF.ResponseSegmentBF;
import com.starmount.ups.sears.responses.segmentBF.SegmentBFLengthCalculator;
import com.starmount.ups.sears.responses.segmentE3.ResponseSegmentE3;
import com.starmount.ups.sears.responses.segmentE3.SegmentE3LengthCalculator;
import com.starmount.ups.sears.responses.segmentE8.ResponseSegmentE8;
import com.starmount.ups.sears.responses.segmentE8.SegmentE8LengthCalculator;
import com.starmount.ups.sears.responses.segmentE9.ResponseSegmentE9;
import com.starmount.ups.sears.responses.segmentE9.SegmentE9LengthCalculator;
import com.starmount.ups.sears.responses.segmentEA.ResponseSegmentEA;
import com.starmount.ups.sears.responses.segmentEA.SegmentEALengthCalculator;
import com.starmount.ups.sears.responses.segmentEC.ResponseSegmentEC;
import com.starmount.ups.sears.responses.segmentEC.SegmentECLengthCalculator;

import com.searshc.mercy.segments.CouponInquiry2AA7Segment;
import com.searshc.mercy.segments.PluInquiryD8Segment;
import com.searshc.mercy.segments.Segment2AA7LengthCalculator;


public class UpasResponseParser{

  private static Logger logger = Logger.getLogger(UpasResponseParser.class);
  private final SegmentFactory factory = new SegmentFactory();
  
  public List<SegmentIndex> parseResponse(byte[] buf) throws Exception{
    
      final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      outputStream.write(createXMLResponseHeader());
      outputStream.write(buf);
      
      ByteBuffer buffer = ByteBuffer.wrap(outputStream.toByteArray());
      SearsMessageParser parser = SearsMessageParser.createBuilder()
              .setBuffer(buffer)
              .addSegments(createSearsResponseTypes())
              .build();
      
      List<SegmentIndex> segmentIndexes = parser.getSegmentList();
      for (SegmentIndex segmentIndex : segmentIndexes) {
        factory.getSegment(segmentIndex.getIndicatorString(), segmentIndex.getBuffer());
        System.out.println("Segment " + segmentIndex.getIndicatorString() + "\t at position " 
            + segmentIndex.getPosition() + " length of\t " 
            + segmentIndex.getLength());
      }
      return segmentIndexes;
  }
  
  private Map<String, SearsResponseType> createSearsResponseTypes() {
    Map<String, SearsResponseType> map = new HashMap<String, SearsResponseType>();
  
    SearsResponseType srt;
    
    srt = new SearsResponseType(ResponseSegment95.INDICATOR.HEX_STRING.getHexString(), ResponseSegment95.class, Segment95LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
   
    srt = new SearsResponseType(ResponseSegment98.INDICATOR.HEX_STRING.getHexString(), ResponseSegment98.class, Segment98LengthCalculator.class );
    map.put(srt.getIndicator(), srt);

    srt = new SearsResponseType(ResponseSegment9C.INDICATOR.HEX_STRING.getHexString(), ResponseSegment9C.class, Segment9CLengthCalculator.class );
    map.put(srt.getIndicator(), srt);

    srt = new SearsResponseType(ResponseSegmentE8.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentE8.class, SegmentE8LengthCalculator.class );
    map.put(srt.getIndicator(), srt);

    srt = new SearsResponseType(ResponseSegmentEC.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentEC.class, SegmentECLengthCalculator.class );
    map.put(srt.getIndicator(), srt);

    srt = new SearsResponseType(ResponseSegment40BA.INDICATOR.HEX_STRING.getHexString(), ResponseSegment40BA.class, Segment40BALengthCalculator.class );
    map.put(srt.getIndicator(), srt);
   
    srt = new SearsResponseType(ResponseSegment2AB7.INDICATOR.HEX_STRING.getHexString(), ResponseSegment2AB7.class, Segment2AB7LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment11B6.INDICATOR.HEX_STRING.getHexString(), ResponseSegment11B6.class, Segment11B6LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment12B1.INDICATOR.HEX_STRING.getHexString(), ResponseSegment12B1.class, Segment12B1LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment48B1.INDICATOR.HEX_STRING.getHexString(), ResponseSegment48B1.class, Segment48B1LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment58B1.INDICATOR.HEX_STRING.getHexString(), ResponseSegment58B1.class, Segment58B1LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment60B1.INDICATOR.HEX_STRING.getHexString(), ResponseSegment60B1.class, Segment60B1LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment62B1.INDICATOR.HEX_STRING.getHexString(), ResponseSegment62B1.class, Segment62B1LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment70B4.INDICATOR.HEX_STRING.getHexString(), ResponseSegment70B4.class, Segment70B4LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment72B2.INDICATOR.HEX_STRING.getHexString(), ResponseSegment72B2.class, Segment72B2LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment72B6.INDICATOR.HEX_STRING.getHexString(), ResponseSegment72B6.class, Segment72B6LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegment72B7.INDICATOR.HEX_STRING.getHexString(), ResponseSegment72B7.class, Segment72B7LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegmentBF.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentBF.class, SegmentBFLengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegmentE3.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentE3.class, SegmentE3LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegmentE9.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentE9.class, SegmentE9LengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    srt = new SearsResponseType(ResponseSegmentEA.INDICATOR.HEX_STRING.getHexString(), ResponseSegmentEA.class, SegmentEALengthCalculator.class );
    map.put(srt.getIndicator(), srt);
    
    return map;
  }
  
  protected byte[] createXMLResponseHeader() {
    String s =
            "<POSRESP><type>NR45</type><unitNumber>00000</unitNumber><id></id><returnCode>0</returnCode><responseDescription>success</responseDescription></POSRESP>";
    return s.getBytes();
  }
}