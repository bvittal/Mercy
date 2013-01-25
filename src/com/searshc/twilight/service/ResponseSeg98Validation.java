package com.searshc.twilight.service;

import junit.framework.AssertionFailedError;
import org.apache.log4j.Logger;

import com.starmount.ups.sears.responses.segment98.ResponseSegment98;

public class ResponseSeg98Validation implements Segment{

  private static Logger logger = Logger.getLogger(ResponseSeg98Validation.class);
  private String indicator;
  boolean valid = Boolean.FALSE;
  
    public ResponseSeg98Validation(byte [] buf) {
      
        ResponseSegment98 segment = new ResponseSegment98(buf);

        try{
          
        this.indicator = segment.getIndicator();

        logger.info("Indicator                    : " + indicator);
        logger.info("futurePurchaseCouponID       : " + segment.getFuturePurchaseCouponID());
        logger.info("startDate                    : " + segment.getStartDate());
        logger.info("endDate                      : " + segment.getEndDate());
        logger.info("minimumQualificationAmount   : " + segment.getMinQualificationAmount());
        logger.info("maximumQuantity              : " + segment.getMaxQty());
        logger.info("searsChargeFlag              : " + segment.getSearsChargeFlag());
        logger.info("regularPriceFlag             : " + segment.getRegularPriceFlag());
        logger.info("promotionPriceFlag           : " + segment.getPromotionPriceFlag());
        logger.info("clearancePriceFlag           : " + segment.getClearancePriceFlag());
        logger.info("mallFlag                     : " + segment.getMallFlag());
        logger.info("hardwareFlag                 : " + segment.getHardwareFlag());
        logger.info("theGreatIndoorsFlag          : " + segment.getGreatIndoorsFlag());
        logger.info("automotiveFlag               : " + segment.getAutomotiveFlag());
        logger.info("outletFlag                   : " + segment.getOutletFlag());
        logger.info("dealerFlag                   : " + segment.getDealerFlag());
        logger.info("wwwFlag                      : " + segment.getWWWFlag());
        logger.info("productServiceFlag           : " + segment.getProductServiceFlag());
        logger.info("deliveryFlag                 : " + segment.getDeliveryFlag());
        logger.info("associateDiscountFlag        : " + segment.getAssocDiscountFlag());
        logger.info("couponNumber                 : " + segment.getCouponNo());
        logger.info("couponTypeCode               : " + segment.getCouponTypeCode());
        logger.info("markdownTypeCode             : " + segment.getMarkDownTypeCode());
        logger.info("markdownAmountPercent        : " + segment.getMarkDownAmount());
        logger.info("futurePurchaseCouponDesc     : " + segment.getFuturePurchaseDescription());

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
