package com.searshc.mercy.service;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segment9C.ResponseSegment9C;

public class ResponseSeg9CValidation implements Segment{

  private static Logger logger = Logger.getLogger(ResponseSegEAValidation.class);
  private String indicator;
  boolean valid = Boolean.FALSE;
  
    public ResponseSeg9CValidation(byte [] buf) {
      
        ResponseSegment9C segment = new ResponseSegment9C(buf);

        try{
          
        this.indicator = segment.getIndicator();
          
        logger.info("Indicator         : " + indicator);
        logger.info("Segment Length    : " + segment.getPLUSegmentLength());
        logger.info("Message Key       : " + segment.getPLUMessageKey());
        logger.info("Message Type Code : " + segment.getPLUMessageTypeCode());
        logger.info("Register Text     : " + segment.getPLURegisterText());
        logger.info("Receipt Text      : " + segment.getPLUReceiptText());
        
        }
        catch (AssertionFailedError ex)
        {
          valid = Boolean.TRUE;
          logger.error("ResponseSeg9CValidation " + ex.getMessage());
        }
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
