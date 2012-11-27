package com.searshc.twilight.service;

import static junit.framework.Assert.assertEquals;
import java.nio.ByteBuffer;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segment60B1.ResponseSegment60B1;
import com.starmount.ups.sears.responses.segment60B1.Segment60B1LengthCalculator;

public class ResponseSeg60B1Validation implements Segment
{
  private String indicator;
  private String offerId;
  private boolean valid = Boolean.FALSE;
  
  private static Logger logger = Logger.getLogger(ResponseSeg60B1Validation.class);
      
      public ResponseSeg60B1Validation(byte [] buf) {

          ResponseSegment60B1 segment = new ResponseSegment60B1(buf);
          
          try
          {
            
            this.indicator = segment.getIndicator();
            this.offerId = segment.getOfferID();
            
          logger.info("Indicator                                  : " + this.indicator);
          logger.info("Segment Level                              : " + segment.getSegmentLevel());
          logger.info("Offer Type                                 : " + segment.getOfferType());
          logger.info("Offer ID                                   : " + this.offerId);
          logger.info("Financial Code                             : " + segment.getFinancialCode());
          logger.info("Threshold Dollar Amount                    : " + segment.getThresholdDollars());
          logger.info("Asscociate Discount Flag                   : " + segment.isAssociateDiscount());
          logger.info("Misc Reductions Flag                       : " + segment.isMiscReductionsFlag());
          logger.info("Instant Rebate Flag                        : " + segment.isInstantRebateFlag());
          logger.info("Mail-In Rebate Flag                        : " + segment.isMailInRebateFlag());
          logger.info("Delayed Billing End Date Interval          : " + segment.getDelayedBillingEndDateInterval());
          logger.info("Delayed Billing End Date                   : " + segment.getDelayedBillingEndDate());
          logger.info("Register Promo Description Line 1          : " + segment.getRegisterPromoDescLine1());
          logger.info("Register Promo Description Line 2          : " + segment.getRegisterPromoDescLine2());
          logger.info("No. of Register Promo Descriptions Lines   : " + segment.getRegisterPromoDescLine2());
          logger.info("Register Promo Description Lines           : " + segment.getReceiptPromoDescLines());

          assertEquals("60B1", segment.getIndicator());
          assertEquals(false, segment.isAssociateDiscount());
          assertEquals(false, segment.isMiscReductionsFlag());
          assertEquals(false, segment.isInstantRebateFlag());
          assertEquals(false, segment.isMailInRebateFlag());
          
          this.LengthCalculator60B1Test(buf);
          
          }
          catch (AssertionFailedError ex)
          {
            valid = Boolean.TRUE;
            logger.error("ResponseSeg60B1Validation " + ex.getMessage());
          }
        }
      
      public void LengthCalculator60B1Test(byte[] buffer) throws AssertionFailedError {
          Segment60B1LengthCalculator lc = new Segment60B1LengthCalculator("60B1", ByteBuffer.wrap(buffer));
          assertEquals("60B1", lc.getIndicator());
          assertEquals(buffer.length, lc.getLength());
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
