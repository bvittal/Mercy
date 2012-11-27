package com.searshc.twilight.service;

import junit.framework.AssertionFailedError;
import org.apache.log4j.Logger;
import com.searshc.twilight.segments.PluInquiryD8Segment;

public class ResponseSegD8Validation implements Segment{
  
  private static Logger logger = Logger.getLogger(ResponseSeg2AA7Validation.class);
  private String indicator;
  private String pluDivisionNumber;
  private String pluItemNumber;
  private String pluSegmentLevel;
  private String pluInquiryType;
  private String pluSKU;
  
  private boolean valid = Boolean.FALSE;
  
    public ResponseSegD8Validation(byte[] buf)
    {
      try {
        PluInquiryD8Segment segment = new PluInquiryD8Segment(buf);
        
        this.indicator = segment.getIndicator();
        this.pluDivisionNumber = segment.getPLUDivisionNumber();
        this.pluItemNumber = segment.getPLUItemNumber();
        this.pluSegmentLevel = segment.getPLUSegmentLevel();
        this.pluInquiryType = segment.getPLUInquiryTrpe();
        this.pluSKU = segment.getPLUSKU();
        
      logger.info("Indicator                      : " + this.indicator);
      logger.info("PLU Division Number            : " + this.pluDivisionNumber);
      logger.info("Segment Level                  : " + this.pluSegmentLevel);
      logger.info("PLU Item Number                : " + this.pluItemNumber);
      logger.info("PLU Inquiry Type               : " + this.pluInquiryType);
      logger.info("PLU SKU                        : " + this.pluSKU);
      
      }catch (AssertionFailedError ex) {
        logger.error("ResponseSegD8Validation " + ex.getMessage());
        valid = Boolean.TRUE;
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
      return this.pluDivisionNumber;
    }

    @Override
    public String pluItemNumber()
    {
      return this.pluItemNumber;
    }

    @Override
    public String pluSKU()
    {
      return this.pluSKU;
    }
}
