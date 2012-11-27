package com.searshc.twilight.service;

import static junit.framework.Assert.assertEquals;

import java.nio.ByteBuffer;

import junit.framework.AssertionFailedError;
import org.apache.log4j.Logger;
import com.starmount.ups.sears.responses.segmentE3.ResponseSegmentE3;
import com.starmount.ups.sears.responses.segmentE3.SegmentE3LengthCalculator;

public class ResponseSegE3Validation implements Segment{
  
  private static Logger logger = Logger.getLogger(ResponseSegECValidation.class);
  private String indicator;
  private boolean valid = Boolean.FALSE;
      
    public ResponseSegE3Validation(byte [] buf){
    
      ResponseSegmentE3 segment = new ResponseSegmentE3(buf);
    
    try{
      
      this.indicator = segment.getIndicator();
  
    logger.info("Indicator            : " + this.indicator);
    logger.info("File Name            : " + segment.getFileName());
    logger.info("File Length          : " + segment.getFileLength());
    logger.info("Sequence Number      : " + segment.getSequenceNumber());
    logger.info("Last Indicator       : " + segment.getLastIndicator());
    
    assertEquals("E3", segment.getIndicator());
    
    this.lengthCalculatorE3Test(buf);
    
    }
    catch (AssertionFailedError ex)
    {
      valid = Boolean.TRUE;
      logger.error("ResponseSegE3Validation " + ex.getMessage());
    }
}
    
    public void lengthCalculatorE3Test(byte[] buffer) throws AssertionFailedError {
      byte[] ba = buffer;
      SegmentE3LengthCalculator calc = new SegmentE3LengthCalculator("E3", ByteBuffer.wrap(ba));
      int i = calc.getLength();
      assertEquals(ba.length, i);
  }

  @Override
  public boolean isValid()
  {
    return this.valid;
  }

  @Override
  public String couponNumber()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String fieldIndicator()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String pluDivisionNumber()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String pluItemNumber()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String pluSKU()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
