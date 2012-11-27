package com.searshc.twilight.service;

import java.nio.ByteBuffer;

import static junit.framework.Assert.assertEquals;
import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segmentEA.ResponseSegmentEA;
import com.starmount.ups.sears.responses.segmentEA.SegmentEALengthCalculator;

public class ResponseSegEAValidation implements Segment{
  
  private static Logger logger = Logger.getLogger(ResponseSegEAValidation.class);
  private String indicator;
  boolean valid = Boolean.FALSE;

    public ResponseSegEAValidation(byte [] buf) {
      
        ResponseSegmentEA segment = new ResponseSegmentEA(buf);
        
        try{
          
          this.indicator = segment.getIndicator();
          
          logger.info("Indicator            : " + indicator);
          logger.info("ErrorType            : " + segment.getErrorType());
          logger.info("Message              : " + segment.getErrorMessage());

          //assertEquals("EA", segment.getIndicator());
          //assertEquals("1", segment.getErrorType());
          //assertEquals("UPC NUMBER NOT FOUND", segment.getErrorMessage());
          
          lengthCalculatorEATest(buf);
          
        }
        catch (AssertionFailedError ex)
        {
          valid = Boolean.TRUE;
          logger.error("ResponseSegEAValidation " + ex.getMessage());
        }
    }
    
    public void lengthCalculatorEATest(byte[] buffer) throws AssertionFailedError{
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        SegmentEALengthCalculator lc = new SegmentEALengthCalculator("EA", bb);
        assertEquals("EA", lc.getIndicator());
        assertEquals(22, lc.getLength());
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
