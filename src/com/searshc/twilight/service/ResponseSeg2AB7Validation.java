package com.searshc.twilight.service;

import static junit.framework.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.nio.ByteBuffer;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segment2AB7.ResponseSegment2AB7;
import com.starmount.ups.sears.responses.segment2AB7.ResponseSegment2AB7.SegmentInclExcl;
import com.starmount.ups.sears.responses.segment2AB7.Segment2AB7LengthCalculator;

public class ResponseSeg2AB7Validation implements Segment{
	
	private static Logger logger = Logger.getLogger(ResponseSeg2AB7Validation.class);
	private String indicator;
	private String couponNumber;
  private boolean valid = Boolean.FALSE;
  
    public ResponseSeg2AB7Validation(byte[] buf)
    {
      try {
        
      ResponseSegment2AB7 segment = new ResponseSegment2AB7(buf);
      
      this.indicator = segment.getIndicator();
      this.couponNumber = segment.getCouponNumber();
      
      logger.info("Indicator                      : " + this.indicator);
      logger.info("Segment Length                 : " + segment.getSegmentLength());
      logger.info("Segment Level                  : " + segment.getSegmentLevel());

      logger.info("Coupon Number                  : " + couponNumber);
      logger.info("Type                           : " + segment.getType());
      logger.info("Start Date                     : " + segment.getStartDate());
      logger.info("Start Time                     : " + segment.getStartTime());
      logger.info("End Date                       : " + segment.getEndDate());
      logger.info("End Time                       : " + segment.getEndTime());
      logger.info("Reduction Type                 : " + segment.getReductionType());
      logger.info("Reduction Amount               : " + segment.getReductionAmount());
      logger.info("Reduction Flag                 : " + segment.getReductionFlag());
      logger.info("Retail Format                  : " + segment.getRetailFormat());
      logger.info("Count Coupon                   : " + segment.getCountCoupon());
      logger.info("One coupon per transaction     : " + segment.getOneCouponPerTransaction());
      logger.info("One coupon per item            : " + segment.getOneCouponPerItem());
      logger.info("Single Use coupon              : " + segment.getSingleUseCoupon());
      logger.info("Initial purchase threshold     : " + segment.getInitialPurchaseThreshold());
      logger.info("Signal Item                    : " + segment.getSignalItem());
      logger.info("Price match                    : " + segment.getPriceMatch());
      logger.info("Xvl Value                      : " + segment.getXvlValue());
      logger.info("Marketing Code                 : " + segment.getMarketingCode());
      logger.info("Item Level Coupon              : " + segment.getItemLevelCoupon());
      logger.info("Manager Approval Code          : " + segment.getManagerApprovalFlag());
      logger.info("Friends Family Coupon          : " + segment.getFriendsFamilyCoupon());
      logger.info("Discount Sears Coupon          : " + segment.getDiscountSearsCoupon());
      logger.info("Valid 0% percent               : " + segment.getValidPercentFinancing());
      logger.info("Regular Price Coupon           : " + segment.getRegPriceCoupon());
      logger.info("No. of Inc/Excl                : " + segment.getNoIncExcl());

      List<ResponseSegment2AB7.SegmentInclExcl> arrList = segment.getIncExclAsSegmentInclExcl();

      Iterator<SegmentInclExcl> iterator = arrList.iterator();

      while (iterator.hasNext()) {
          SegmentInclExcl obj = iterator.next();

          logger.info("IncExcl Sq Number = " + obj.getSequenceNumber());
          logger.info("\tIncExcl Inc Flag = " + obj.isIncFlag());
          logger.info("\tIncExcl Division Nbr = " + obj.getDivisionNbr());
          logger.info("\tIncExcl Line Nbr = " + obj.getLineNbr());
          logger.info("\tIncExcl SubLine Nbr = " + obj.getSublineNbr());
          logger.info("\tIncExcl SubLine Var= " + obj.getSublineVar());
      }

      assertEquals(true, segment.isSearsAccount());
      assertEquals(true, segment.isRegularPrice());
      assertEquals(true, segment.isPromotionPrice());
      assertEquals(false, segment.isClearencePrice());
      assertEquals(true, segment.isAssociateDiscountEligible());
      assertEquals(false, segment.isRestrictiveMinimumPurchase());

      assertEquals(true, segment.isStoreTypeMall());
      assertEquals(true, segment.isStoreTypePaintHardware());
      assertEquals(true, segment.isStoreTypeDealer());
      assertEquals(false, segment.isStoreTypeAutomotive());
      assertEquals(true, segment.isStoreTypeTGI());
      assertEquals(false, segment.isStoreTypeWWW());
      assertEquals(true, segment.isStoreTypeSearsGrandEssential());
      assertEquals(true, segment.isStoreTypeOrchard());
      assertEquals(true, segment.isStoreTypeKmart());
      
      this.lengthCalculator2AB7Test(buf);
      
      }catch (AssertionFailedError ex) {
        logger.error("ResponseSeg2AB7Validation " + ex.getMessage());
         valid = Boolean.TRUE;
      }
    }
    
    public void lengthCalculator2AB7Test(byte[] buffer) throws AssertionFailedError{
        byte[] ba = buffer;
        ByteBuffer bb = ByteBuffer.wrap(ba);
        Segment2AB7LengthCalculator lc = new Segment2AB7LengthCalculator("2AB7", bb);
        assertEquals("2AB7", lc.getIndicator());
        assertEquals(ba.length, lc.getLength());
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
