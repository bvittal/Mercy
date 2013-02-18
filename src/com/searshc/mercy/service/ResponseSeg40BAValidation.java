package com.searshc.mercy.service;

import static junit.framework.Assert.assertEquals;
import junit.framework.AssertionFailedError;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segment40BA.ResponseSegment40BA;
import com.starmount.ups.sears.responses.segment40BA.Segment40BALengthCalculator;

public class ResponseSeg40BAValidation implements Segment
{
  private static Logger logger = Logger.getLogger(ResponseSeg40BAValidation.class);
  private String indicator;
  private boolean valid = Boolean.FALSE;
  
    public ResponseSeg40BAValidation(byte [] buf){
      
      ResponseSegment40BA segment = new ResponseSegment40BA(buf);
      
      try{
        
        this.indicator = segment.getIndicator();
        
      logger.info("Indicator                      : " + this.indicator);
      logger.info("Segment Length                 : " + segment.getSegmentLength());
      logger.info("Segment Level                  : " + segment.getSegmentLevel());
      logger.info("Tax Status Code                : " + segment.getTaxCodeStatus());
      logger.info("Fill Floor Eligible            : " + segment.isFillFloorEligible());
      logger.info("SKU991 Eligible                : " + segment.isSKU991Eligible());
      logger.info("Unused                         : " + segment.getUnused() );
      logger.info("Exception Value Item           : " + segment.isExceptionalValueItem());
      logger.info("PA Replacement Eligible        : " + segment.getPAReplacementEligible());
      logger.info("Signal Item                    : " + segment.isSignalItem());
      logger.info("Restocking Fee Eligible        : " + segment.isRestockingFeeElible());
      logger.info("Cancellation Fee Eligible      : " + segment.isCancellationFeeEligible());
      logger.info("Prepaid Card Item Eligible     : " + segment.getPrePaidCardItem());
      logger.info("Converter Box Item             : " + segment.isConverterBoxItem());
      logger.info("IIAS Elgible Item              : " + segment.isIIASEligibleItem());
      logger.info("Associate Discount Ineligible  : " + segment.isAssociateDiscountIneligible());
      logger.info("Subscription Plan Eligible     : " + segment.isSubscriptionPlanEligible());
      logger.info("Third Party Item (Edwin Watts) : " + segment.isThirdPartyItemEdwinWatts());
      logger.info("Alphaline Entertainment        : " + segment.isAlphaLineEntertainment());
      logger.info("SYWR Redemption Exclusion      : " + segment.isSYWRRedemptionExclusion());
      logger.info("Unused2                        : " + segment.getUnused2());
      logger.info("Restocking Fee Percent         : " + segment.getRestockFeePercent());
      logger.info("Cancellation Fee Percent       : " + segment.getCancellationFeePercent());
      
      this.lengthCalculator40BATest(buf);
      
      }
      catch (AssertionFailedError ex) 
      {
        logger.error("ResponseSeg40BAValidation " + ex.getMessage());
        valid = Boolean.TRUE;
      }
    }

    public void lengthCalculator40BATest(byte [] buffer) throws AssertionFailedError{
      ByteBuffer bb = ByteBuffer.wrap(buffer);
      Segment40BALengthCalculator lc = new Segment40BALengthCalculator("40BA", bb);
      assertEquals("40BA", lc.getIndicator());
      assertEquals(31, lc.getLength());
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
