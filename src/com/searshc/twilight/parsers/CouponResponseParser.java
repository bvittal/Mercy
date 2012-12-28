package com.searshc.twilight.parsers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.TwilightJsonObject;
import com.searshc.twilight.util.PropertyLoader;

public class CouponResponseParser
{
  private static Logger logger = Logger.getLogger(CouponResponseParser.class);
  private static Properties prop;
  
  //Coupon Inquiry Response - 2AB7 - parameters
  private static final String COUPON_RESP_SEGMENT_LENGTH = "segmentLength";
  private static final String COUPON_RESP_SEGMENT_LEVEL = "segmentLevel";
  private static final String COUPON_RESP_COUPON_NUMBER = "couponNumber";
  private static final String COUPON_RESP_COUPON_TYPE = "type";
  private static final String COUPON_RESP_START_DATE = "startDate";
  private static final String COUPON_RESP_START_TIME = "startTime";
  private static final String COUPON_RESP_END_DATE = "endDate";
  private static final String COUPON_RESP_END_TIME = "endTime";
  private static final String COUPON_RESP_REDUCTION_TYPE =  "reductionType";
  private static final String COUPON_RESP_REDUCTION_AMOUNT = "reductionAmount";
  private static final String COUPON_RESP_REDUCTION_FLAG = "reductionFlag";
  private static final String COUPON_RESP_RETAIL_FORMAT = "retailFormat";
  private static final String COUPON_RESP_COUNT_COUPON = "countCoupon";
  private static final String COUPON_RESP_ONE_COUPON_PER_TRANSACTION = "oneCouponPerTransaction";
  private static final String COUPON_RESP_ONE_COUPON_PER_ITEM = "oneCouponPerItem";
  private static final String COUPON_RESP_SINGLE_USE_COUPON =  "singleUseCoupon";
  private static final String COUPON_RESP_INITIAL_PURCHASE_THRESHOLD = "initialPurchaseThreshold";
  private static final String COUPON_RESP_SIGNAL_ITEM = "signalItem";
  private static final String COUPON_RESP_PRICE_MATCH = "priceMatch";
  private static final String COUPON_RESP_GREAT_VALUE = "greatValue";
  private static final String COUPON_RESP_MARKETING_CODE = "marketingCode";
  private static final String COUPON_RESP_ITEM_LEVEL_COUPON = "itemLevelCoupon";
  private static final String COUPON_RESP_MANAGER_APPROVAL_FLAG = "managerApprovalFlag";
  private static final String COUPON_RESP_FRIENDS_AND_FAMILY_COUPON = "friendsAndFamilyCoupon";
  private static final String COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON = "discountWithSearsCardCoupon";
  private static final String COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING = "validWithZeroPercentFinancing";
  private static final String COUPON_RESP_REGULAR_PRICE_COUPON = "regularPriceCoupon";
  private static final String COUPON_RESP_NBR_OF_INCL_EXCL = "nbrOfInclExcl";
  
  private static final String COUPON_RESP_INCL_EXCL_SEQ_NUMBER = "sequenceNumber";
  private static final String COUPON_RESP_INCL_EXCL_FLAG = "incExcFlag";
  private static final String COUPON_RESP_INCL_EXCL_DIVISION_NUMBER = "divisionNumber";
  private static final String COUPON_RESP_INCL_EXCL_LINE_NUMBER = "lineNumber";
  private static final String COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER = "subLineNumber";
  private static final String COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE = "subLineVariable";
  private static final String COUPON_RESP_INCL_EXCL_ITEM_NUMBER = "itemNumber";
  
  //Coupon Inquiry Response - 2AB7 - default Values
  private static final String DEFAULT_COUPON_RESP_SEGMENT_LENGTH = "couponResp_2AB7_segmentLength";
  private static final String DEFAULT_COUPON_RESP_SEGMENT_LEVEL = "couponResp_2AB7_segmentLevel";
  private static final String DEFAULT_COUPON_RESP_COUPON_NUMBER = "couponResp_2AB7_couponNumber";
  private static final String DEFAULT_COUPON_RESP_COUPON_TYPE = "couponResp_2AB7_type";
  private static final String DEFAULT_COUPON_RESP_START_DATE = "couponResp_2AB7_startDate";
  private static final String DEFAULT_COUPON_RESP_START_TIME = "couponResp_2AB7_startTime";
  private static final String DEFAULT_COUPON_RESP_END_DATE = "couponResp_2AB7_endDate";
  private static final String DEFAULT_COUPON_RESP_END_TIME = "couponResp_2AB7_endTime";
  private static final String DEFAULT_COUPON_RESP_REDUCTION_TYPE =  "couponResp_2AB7_reductionType";
  private static final String DEFAULT_COUPON_RESP_REDUCTION_AMOUNT = "couponResp_2AB7_reductionAmount";
  private static final String DEFAULT_COUPON_RESP_REDUCTION_FLAG = "couponResp_2AB7_reductionFlag";
  private static final String DEFAULT_COUPON_RESP_RETAIL_FORMAT = "couponResp_2AB7_retailFormat";
  private static final String DEFAULT_COUPON_RESP_COUNT_COUPON = "couponResp_2AB7_countCoupon";
  private static final String DEFAULT_COUPON_RESP_ONE_COUPON_PER_TRANSACTION = "couponResp_2AB7_oneCouponPerTransaction";
  private static final String DEFAULT_COUPON_RESP_ONE_COUPON_PER_ITEM = "couponResp_2AB7_oneCouponPerItem";
  private static final String DEFAULT_COUPON_RESP_SINGLE_USE_COUPON =  "couponResp_2AB7_singleUseCoupon";
  private static final String DEFAULT_COUPON_RESP_INITIAL_PURCHASE_THRESHOLD = "couponResp_2AB7_initialPurchaseThreshold";
  private static final String DEFAULT_COUPON_RESP_SIGNAL_ITEM = "couponResp_2AB7_signalItem";
  private static final String DEFAULT_COUPON_RESP_PRICE_MATCH = "couponResp_2AB7_priceMatch";
  private static final String DEFAULT_COUPON_RESP_GREAT_VALUE = "couponResp_2AB7_greatValue";
  private static final String DEFAULT_COUPON_RESP_MARKETING_CODE = "couponResp_2AB7_marketingCode";
  private static final String DEFAULT_COUPON_RESP_ITEM_LEVEL_COUPON = "couponResp_2AB7_itemLevelCoupon";
  private static final String DEFAULT_COUPON_RESP_MANAGER_APPROVAL_FLAG = "couponResp_2AB7_managerApprovalFlag ";
  private static final String DEFAULT_COUPON_RESP_FRIENDS_AND_FAMILY_COUPON = "couponResp_2AB7_friendsAndFamilyCoupon";
  private static final String DEFAULT_COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON = "couponResp_2AB7_discountWithSearsCardCoupon";
  private static final String DEFAULT_COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING = "couponResp_2AB7_validWithZeroPercentFinancing";
  private static final String DEFAULT_COUPON_RESP_REGULAR_PRICE_COUPON = "couponResp_2AB7_regularPriceCoupon";
  private static final String DEFAULT_COUPON_RESP_NBR_OF_INCL_EXCL = "couponResp_2AB7_nbrOfInclExcl";
  
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_SEQ_NUMBER = "couponResp_2AB7_sequenceNumber";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_FLAG = "couponResp_2AB7_incExcFlag";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_DIVISION_NUMBER = "couponResp_2AB7_divisionNumber";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_LINE_NUMBER = "couponResp_2AB7_lineNumber";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER = "couponResp_2AB7_subLineNumber";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE = "couponResp_2AB7_subLineVariable";
  private static final String DEFAULT_COUPON_RESP_INCL_EXCL_ITEM_NUMBER = "couponResp_2AB7_itemNumber";
  
  
  public CouponResponseParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getCouponResponse(TwilightJsonObject twilightJsonObject)
  {
    StringBuilder builder = new StringBuilder();
    builder = this.processCouponResponse2AB7(twilightJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processCouponResponse2AB7(TwilightJsonObject twilightJsonObject)
  {
      String indicator = "2A B7";
      String segmentLength = StringUtils.EMPTY;
      String segmentLevel = StringUtils.EMPTY;
      String couponNumber = StringUtils.EMPTY;
      String type = StringUtils.EMPTY;
      String startDate = StringUtils.EMPTY;
      String startTime = StringUtils.EMPTY;
      String endDate = StringUtils.EMPTY;
      String endTime = StringUtils.EMPTY;
      String reductionType = StringUtils.EMPTY;
      String reductionAmount = StringUtils.EMPTY;
      String reductionFlag = StringUtils.EMPTY;
      String retailFormat = StringUtils.EMPTY;
      String countCoupon = StringUtils.EMPTY;
      String oneCouponPerTransaction = StringUtils.EMPTY;
      String oneCouponPerItem = StringUtils.EMPTY;
      String singleUseCoupon = StringUtils.EMPTY;
      String initialPurchaseThreshold = StringUtils.EMPTY;
      String signalItem = StringUtils.EMPTY;
      String priceMatch = StringUtils.EMPTY;
      String greatValue = StringUtils.EMPTY;
      String marketingCode = StringUtils.EMPTY;
      String itemLevelCoupon = StringUtils.EMPTY;
      String managerApprovalFlag = StringUtils.EMPTY;
      String friendsAndFamilyCoupon = StringUtils.EMPTY;
      String discountWithSearsCardCoupon = StringUtils.EMPTY;
      String validWithZeroPercentFinancing = StringUtils.EMPTY;
      String regularPriceCoupon = StringUtils.EMPTY;
      String nbrOfInclExcl = StringUtils.EMPTY;
      
      String sequenceNumber = StringUtils.EMPTY;
      String incExcFlag = StringUtils.EMPTY;
      String divisionNumber = StringUtils.EMPTY;
      String lineNumber = StringUtils.EMPTY;
      String subLineNumber = StringUtils.EMPTY;
      String subLineVariable = StringUtils.EMPTY;
      String itemNumber = StringUtils.EMPTY;
      
      int calculatedSegmentLength =0;
      
      final Map<String, String> couponResponseMap = twilightJsonObject.getParameters();
      final Map<String, String> modifiableMap = new HashMap<String,String>();
      final StringBuilder sb = new StringBuilder();
      
    if(couponResponseMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : couponResponseMap.entrySet())
      { 
        if(!couponResponseMap.containsKey(COUPON_RESP_SEGMENT_LENGTH))
        {
            modifiableMap.put(COUPON_RESP_SEGMENT_LENGTH, prop.getProperty(DEFAULT_COUPON_RESP_SEGMENT_LENGTH));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_SEGMENT_LEVEL))
        {
            modifiableMap.put(COUPON_RESP_SEGMENT_LEVEL, prop.getProperty(DEFAULT_COUPON_RESP_SEGMENT_LEVEL));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_COUPON_NUMBER))
        {
            modifiableMap.put(COUPON_RESP_COUPON_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_COUPON_NUMBER));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_COUPON_TYPE))
        {
            modifiableMap.put(COUPON_RESP_COUPON_TYPE, prop.getProperty(DEFAULT_COUPON_RESP_COUPON_TYPE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!couponResponseMap.containsKey(COUPON_RESP_START_DATE))
        {
            modifiableMap.put(COUPON_RESP_START_DATE, prop.getProperty(DEFAULT_COUPON_RESP_START_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_START_TIME))
        {
            modifiableMap.put(COUPON_RESP_START_TIME, prop.getProperty(DEFAULT_COUPON_RESP_START_TIME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_END_DATE))
        {
            modifiableMap.put(COUPON_RESP_END_DATE, prop.getProperty(DEFAULT_COUPON_RESP_END_DATE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_END_TIME))
        {
            modifiableMap.put(COUPON_RESP_END_TIME, prop.getProperty(DEFAULT_COUPON_RESP_END_TIME));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        if(!couponResponseMap.containsKey(COUPON_RESP_REDUCTION_TYPE))
        {
            modifiableMap.put(COUPON_RESP_REDUCTION_TYPE, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_TYPE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_REDUCTION_AMOUNT))
        {
            modifiableMap.put(COUPON_RESP_REDUCTION_AMOUNT, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_AMOUNT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_REDUCTION_FLAG))
        {
            modifiableMap.put(COUPON_RESP_REDUCTION_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_RETAIL_FORMAT))
        {
            modifiableMap.put(COUPON_RESP_RETAIL_FORMAT, prop.getProperty(DEFAULT_COUPON_RESP_RETAIL_FORMAT));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_COUNT_COUPON))
        {
            modifiableMap.put(COUPON_RESP_COUNT_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_COUNT_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_ONE_COUPON_PER_TRANSACTION))
        {
            modifiableMap.put(COUPON_RESP_ONE_COUPON_PER_TRANSACTION, prop.getProperty(DEFAULT_COUPON_RESP_ONE_COUPON_PER_TRANSACTION));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_ONE_COUPON_PER_ITEM))
        {
            modifiableMap.put(COUPON_RESP_ONE_COUPON_PER_ITEM, prop.getProperty(DEFAULT_COUPON_RESP_ONE_COUPON_PER_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_SINGLE_USE_COUPON))
        {
            modifiableMap.put(COUPON_RESP_SINGLE_USE_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_SINGLE_USE_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_INITIAL_PURCHASE_THRESHOLD))
        {
            modifiableMap.put(COUPON_RESP_INITIAL_PURCHASE_THRESHOLD, prop.getProperty(DEFAULT_COUPON_RESP_INITIAL_PURCHASE_THRESHOLD));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_SIGNAL_ITEM))
        {
            modifiableMap.put(COUPON_RESP_SIGNAL_ITEM, prop.getProperty(DEFAULT_COUPON_RESP_SIGNAL_ITEM));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_PRICE_MATCH))
        {
            modifiableMap.put(COUPON_RESP_PRICE_MATCH, prop.getProperty(DEFAULT_COUPON_RESP_PRICE_MATCH));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_GREAT_VALUE))
        {
            modifiableMap.put(COUPON_RESP_GREAT_VALUE, prop.getProperty(DEFAULT_COUPON_RESP_GREAT_VALUE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_MARKETING_CODE))
        {
            modifiableMap.put(COUPON_RESP_MARKETING_CODE, prop.getProperty(DEFAULT_COUPON_RESP_MARKETING_CODE));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_ITEM_LEVEL_COUPON))
        {
            modifiableMap.put(COUPON_RESP_ITEM_LEVEL_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_ITEM_LEVEL_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_MANAGER_APPROVAL_FLAG))
        {
            modifiableMap.put(COUPON_RESP_MANAGER_APPROVAL_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_MANAGER_APPROVAL_FLAG));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_FRIENDS_AND_FAMILY_COUPON))
        {
            modifiableMap.put(COUPON_RESP_FRIENDS_AND_FAMILY_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_FRIENDS_AND_FAMILY_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON))
        {
            modifiableMap.put(COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING))
        {
            modifiableMap.put(COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING, prop.getProperty(DEFAULT_COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_REGULAR_PRICE_COUPON))
        {
            modifiableMap.put(COUPON_RESP_REGULAR_PRICE_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_REGULAR_PRICE_COUPON));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
        
        if(!couponResponseMap.containsKey(COUPON_RESP_NBR_OF_INCL_EXCL))
        {
            modifiableMap.put(COUPON_RESP_NBR_OF_INCL_EXCL, prop.getProperty(DEFAULT_COUPON_RESP_NBR_OF_INCL_EXCL));
        }
        else
        {
          modifiableMap.put(entry.getKey(), entry.getValue());
        }
      }
      
      if(modifiableMap.size() > 0)
      {
        for (Map.Entry<String, String> entry : modifiableMap.entrySet())
        { 
         if(entry.getKey().equalsIgnoreCase(COUPON_RESP_SEGMENT_LENGTH))
           segmentLength = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_SEGMENT_LEVEL))
           segmentLevel = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_COUPON_NUMBER))
           couponNumber = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_COUPON_TYPE))
           type = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_START_DATE))
           startDate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_START_TIME))
           startTime = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_END_DATE))
           endDate = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_END_TIME))
           endTime = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_REDUCTION_TYPE))
           reductionType = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_REDUCTION_AMOUNT))
           reductionAmount = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_REDUCTION_FLAG))
           reductionFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_RETAIL_FORMAT))
           retailFormat = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_COUNT_COUPON))
           countCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_ONE_COUPON_PER_TRANSACTION))
           oneCouponPerTransaction = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_ONE_COUPON_PER_ITEM))
           oneCouponPerItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_SINGLE_USE_COUPON))
           singleUseCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INITIAL_PURCHASE_THRESHOLD))
           initialPurchaseThreshold = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_SIGNAL_ITEM))
           signalItem = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_PRICE_MATCH))
           priceMatch = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_GREAT_VALUE))
           greatValue = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_MARKETING_CODE))
         {
           marketingCode = entry.getValue();
           if(StringUtils.isBlank(marketingCode) || marketingCode.equalsIgnoreCase("null"))
             marketingCode = StringUtils.rightPad(StringUtils.EMPTY, 24); //Char(24)  Alphanumeric/special character. Leading spaces displayed. 
         }
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_ITEM_LEVEL_COUPON))
           itemLevelCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_MANAGER_APPROVAL_FLAG))
           managerApprovalFlag = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_FRIENDS_AND_FAMILY_COUPON))
           friendsAndFamilyCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON))
           discountWithSearsCardCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING))
           validWithZeroPercentFinancing = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_REGULAR_PRICE_COUPON))
           regularPriceCoupon = entry.getValue();
         else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_NBR_OF_INCL_EXCL))
           nbrOfInclExcl = entry.getValue();
        }
      }
      sb.append(indicator)
      .append(" ")
      //This logic needs to be revisited once Patrick will be back from holidays
      .append("51 01 ")
      .append(this.byteResponse(segmentLevel.getBytes()))
      .append(this.byteResponse(couponNumber.getBytes()))
      .append(this.byteResponse(type.getBytes()))
      .append(this.byteResponse(startDate.getBytes()))
      .append(this.byteResponse(startTime.getBytes()))
      .append(this.byteResponse(endDate.getBytes()))
      .append(this.byteResponse(endTime.getBytes()))
      .append(this.byteResponse(reductionType.getBytes()))
      .append(this.byteResponse(reductionAmount.getBytes()))
      //.append(this.byteResponse(reductionFlag.getBytes()))
      .append("70 20 ")
      //.append(this.byteResponse(retailFormat.getBytes()))
      .append("80 20 ")
      .append(this.byteResponse(countCoupon.getBytes()))
      .append(this.byteResponse(oneCouponPerTransaction.getBytes()))
      .append(this.byteResponse(oneCouponPerItem.getBytes()))
      .append(this.byteResponse(singleUseCoupon.getBytes()))
      .append(this.byteResponse(initialPurchaseThreshold.getBytes()))
      .append(this.byteResponse(signalItem.getBytes()))
      .append(this.byteResponse(priceMatch.getBytes()))
      .append(this.byteResponse(greatValue.getBytes()))
      .append(this.byteResponse(marketingCode.getBytes()))
      .append(this.byteResponse(itemLevelCoupon.getBytes()))
      .append(this.byteResponse(managerApprovalFlag.getBytes()))
      .append(this.byteResponse(friendsAndFamilyCoupon.getBytes()))
      .append(this.byteResponse(discountWithSearsCardCoupon.getBytes()))
      .append(this.byteResponse(validWithZeroPercentFinancing.getBytes()))
      .append(this.byteResponse(regularPriceCoupon.getBytes()))
      .append(this.byteResponse(nbrOfInclExcl.getBytes()));
    }
        
      
        for(TwilightJsonObject inclExclObj : twilightJsonObject.getTwilightJsonObject())
        {
          final Iterator<TwilightJsonObject> inclExclItr = inclExclObj.getTwilightJsonObject().iterator();
          
          while(inclExclItr.hasNext())
          {
            final Map<String, String> inclExclMap = inclExclItr.next().getParameters();
            final Map<String, String> inclExclModifiableMap = new HashMap<String,String>();
            
            if(inclExclMap.size() > 0)
            {
              for (Map.Entry<String, String> entry : inclExclMap.entrySet())
              {
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_SEQ_NUMBER))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_SEQ_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SEQ_NUMBER));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_FLAG))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_FLAG));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_DIVISION_NUMBER))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_DIVISION_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_DIVISION_NUMBER));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_LINE_NUMBER))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_LINE_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_LINE_NUMBER));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                  
                  if(!inclExclMap.containsKey(COUPON_RESP_INCL_EXCL_ITEM_NUMBER))
                  {
                    inclExclModifiableMap.put(COUPON_RESP_INCL_EXCL_ITEM_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_ITEM_NUMBER));
                  }
                  else
                  {
                    inclExclModifiableMap.put(entry.getKey(), entry.getValue());
                  }
                }
              
                if(inclExclModifiableMap.size() > 0)
                {
                  for (Map.Entry<String, String> entry : inclExclModifiableMap.entrySet())
                  { 
                    if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_SEQ_NUMBER))
                      sequenceNumber = entry.getValue();
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_FLAG))
                    {
                      incExcFlag = entry.getValue();
                      if(StringUtils.isNotBlank(incExcFlag) && incExcFlag.equalsIgnoreCase("true"))
                        incExcFlag = "I";
                      else
                        incExcFlag = "E";
                    }
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_DIVISION_NUMBER))
                      divisionNumber = entry.getValue();
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_LINE_NUMBER))
                      lineNumber = entry.getValue();
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER))
                      subLineNumber = entry.getValue();
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE))
                      subLineVariable = entry.getValue();
                    else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_ITEM_NUMBER))
                      itemNumber = entry.getValue();
                  }
                }
                
              sb.append(this.byteResponse(sequenceNumber.getBytes()))
                .append(this.byteResponse(incExcFlag.getBytes()))
                .append(this.byteResponse(divisionNumber.getBytes()))
                .append(this.byteResponse(lineNumber.getBytes()))
                .append(this.byteResponse(subLineNumber.getBytes()))
                .append(this.byteResponse(subLineVariable.getBytes()));
              }
            }
          }
        return sb;
     }
  
  private String byteResponse(byte[] buffer)
  {
    StringBuilder sb = new StringBuilder();

    for (byte b : buffer)
    {
      sb.append(String.format("%02x", b).toUpperCase());
      sb.append(" ");
    }
    return sb.toString();
  }
  }