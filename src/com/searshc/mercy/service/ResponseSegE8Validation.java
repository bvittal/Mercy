package com.searshc.mercy.service;

import static junit.framework.Assert.assertEquals;
import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segmentE8.ResponseSegmentE8;

public class ResponseSegE8Validation implements Segment
{
  private static Logger logger = Logger.getLogger(ResponseSegE8Validation.class);
  
  private String indicator;
  private String pluDivisionNumber;
  private String pluItemNumber;
  private String pluSKU;
  private boolean valid = Boolean.FALSE;
      
  public ResponseSegE8Validation(byte[] buf)
  {
    
    ResponseSegmentE8 segment = new ResponseSegmentE8(buf);
    
    try{
      
      this.indicator = segment.getIndicator();
      this.pluItemNumber = segment.getPLUItemNumber();
      this.pluDivisionNumber = segment.getPLUDivisionNumber();
      this.pluSKU = segment.getPLUSKU();
    
    logger.info("Indicator            : " + this.indicator);
    logger.info("Division             : " + this.pluDivisionNumber);
    logger.info("Item Number          : " + this.pluItemNumber);
    logger.info("SKU                  : " + this.pluSKU);
    logger.info("Line Number          : " + segment.getPLULineNumber());
    logger.info("Sub Line No          : " + segment.getPLUSubLineNumber());
    logger.info("Sub Line Variable No : " + segment.getPLUSubLineVariableNumber());
    logger.info("Item Description     : " + segment.getPLUItemDescription());
    logger.info("Regular Price        : " + segment.getPLURegularPrice());
    logger.info("Price                : " + segment.getPLUPrice());
    logger.info("Price Type           : " + segment.getPLUPriceType());
    logger.info("Source Order Time    : " + segment.getPLUSourceOrderProcessingTime());
    logger.info("BIAS Count           : " + segment.getPLUBIASCount());
    logger.info("Committed Quantity   : " + segment.getPLUCommittedQuantity());
    logger.info("Product Registration : " + segment.getPLUProductRegistrationAsBinaryString());

    logger.info("\n");
    logger.info(segment.getHexString());
    logger.info(segment.getNiceHexString());
     
    this.BitOpsTest();
    
    }
    catch (AssertionFailedError ex) 
    {
      logger.error("ResponseSegE8Validation " + ex.getMessage());
      valid = Boolean.TRUE;
    }
}

    public void BitOpsTest() throws AssertionFailedError{
        String negativeControlString = "0000 0000 0000 0000 0000 0000 0000 0000".replaceAll(" ", "");
        String positiveControlString = "1111 1111 1111 1111 1111 1111 1111 1111".replaceAll(" ", "");
    
        String productRegistrationMask = "0000 0000 0000 0000 0000 0000 0000 0001".replaceAll(" ", "");
    
        long negativeControlInt = Long.parseLong(negativeControlString, 2);
        long positiveControlInt = Long.parseLong(positiveControlString, 2);
        long productRegistrationMaskInt = Long.parseLong(productRegistrationMask, 2);
    
        logger.info(Long.toBinaryString(negativeControlInt));
        logger.info(Long.toBinaryString(positiveControlInt));
        logger.info(Long.toBinaryString(negativeControlInt & productRegistrationMaskInt));
        logger.info(Long.toBinaryString(positiveControlInt & productRegistrationMaskInt));
    
        assertEquals(0, negativeControlInt & productRegistrationMaskInt);
        assertEquals(1, positiveControlInt & productRegistrationMaskInt);
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
