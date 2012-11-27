package com.searshc.twilight.service;

import static junit.framework.Assert.assertEquals;
import java.math.BigDecimal;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segmentEC.ResponseSegmentEC;

public class ResponseSegECValidation implements Segment{
  
  private static Logger logger = Logger.getLogger(ResponseSegECValidation.class);
  private String indicator;
  private boolean valid = Boolean.FALSE;
      
    public ResponseSegECValidation(byte [] buf){
    
      ResponseSegmentEC segment = new ResponseSegmentEC(buf);
    
    try{
      
      this.indicator = segment.getIndicator();
  
    logger.info("Indicator            : " + this.indicator);
    logger.info("Group ID             : " + segment.getGroupID());
    logger.info("Item Flag            : " + segment.getItemFlag());
    logger.info("Group Type           : " + segment.getGroupType());
    logger.info("Group Qty            : " + segment.getGroupQty());
    logger.info("Group Price          : " + segment.getGroupPrice() );
  
    logger.info("\n");
    logger.info(segment.getHexString());
    logger.info(segment.getNiceHexString());
    
    /**
    assertEquals("EC", segment.getIndicator());
    assertEquals("6004952", segment.getGroupID());
    assertEquals("3", segment.getItemFlag());
    assertEquals("7", segment.getGroupType());
    assertEquals("001", segment.getGroupQty());
    assertEquals("0005000", segment.getGroupPrice());
  
    assertEquals(new BigDecimal("50.00"), segment.getGroupPriceAsDecimal() );
    */
    
    }
    catch (AssertionFailedError ex)
    {
      valid = Boolean.TRUE;
      logger.error("ResponseSegECValidation " + ex.getMessage());
    }
}

  @Override
  public boolean isValid()
  {
    // TODO Auto-generated method stub
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
