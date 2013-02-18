package com.searshc.mercy.service;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.searshc.mercy.segments.FileInquiryD3Segment;

public class ResponseSegD3Validation implements Segment
{

  private static Logger logger = Logger.getLogger(ResponseSegD3Validation.class);

  private String indicator;
  private String fieldIndicator;
  private boolean valid = Boolean.FALSE;

  public ResponseSegD3Validation(byte[] buf)
  {
    FileInquiryD3Segment segment = new FileInquiryD3Segment(buf);
    
    try
    {
      this.indicator = segment.getIndicator();
      this.fieldIndicator = segment.getFieldIdentifier();
      
      logger.info("Indicator                      : " + this.indicator);
      logger.info("Field Indicator                : " + this.fieldIndicator);

    }
    catch (AssertionFailedError ex)
    {
      valid = Boolean.TRUE;
      logger.error("ResponseSeg2AA7Validation " + ex.getMessage());
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
    return null;
  }

  @Override
  public String fieldIndicator()
  {
    return this.fieldIndicator;
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
