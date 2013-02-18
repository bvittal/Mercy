package com.searshc.mercy.service;

import static junit.framework.Assert.assertEquals;
import java.nio.ByteBuffer;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.searshc.mercy.segments.CouponInquiry2AA7Segment;
import com.searshc.mercy.segments.Segment2AA7LengthCalculator;

public class ResponseSeg2AA7Validation implements Segment
{

  private static Logger logger = Logger.getLogger(ResponseSeg2AA7Validation.class);

  private String indicator;
  private String couponNumber;
  private boolean valid = Boolean.FALSE;

  public ResponseSeg2AA7Validation(byte[] buf)
  {
    CouponInquiry2AA7Segment segment = new CouponInquiry2AA7Segment(buf);
    
    try
    {
      this.indicator = segment.getIndicator();
      this.couponNumber = segment.getCouponNumber();
      
      logger.info("Indicator                      : " + this.indicator);
      logger.info("Coupon Number                  : " + this.couponNumber);

      this.lengthCalculator2AA7Test(buf);

    }
    catch (AssertionFailedError ex)
    {
      valid = Boolean.TRUE;
      logger.error("ResponseSeg2AA7Validation " + ex.getMessage());
    }
  }

  public void lengthCalculator2AA7Test(byte[] buffer)
      throws AssertionFailedError
  {
    ByteBuffer bb = ByteBuffer.wrap(buffer);
    Segment2AA7LengthCalculator lc = new Segment2AA7LengthCalculator("2AA7", bb);
    assertEquals("2AA7", lc.getIndicator());
    // assertEquals(ba.length, lc.getLength());
  }

  @Override
  public boolean isValid()
  {
    return this.valid;
  }

  @Override
  public String couponNumber()
  {
    return this.couponNumber;
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
