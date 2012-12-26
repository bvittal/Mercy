package com.searshc.twilight;

import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.common.base.Splitter;
import com.searshc.twilight.service.SegmentFactory;
import com.searshc.twilight.util.PropertyLoader;
import com.starmount.ups.sears.responses.segment2AB7.ResponseSegment2AB7.Segment2AB7Field;

public class ScriptCommandParser
{
  private static Properties prop;
  private HashMap<String,String> cartParameters;
  private List<HashMap> itemParameters = new ArrayList<HashMap>();
  private static Logger logger = Logger.getLogger(ScriptCommandParser.class);
  private TwilightJsonObject obj;
  private StringBuilder byteArrayObj;
  private String action;
  private String method;

  //File Inquiry - D3 - parameters
  private static final String FILE_INQ_FILE_NAME = "fileName";
  private static final String FILE_INQ_PADDING = "padding";
  
  //File Inquiry - D3 - default Values
  private static final String DEFAULT_FILE_INQ_FILE_NAME = "fileInq_fileName";
  private static final String DEFAULT_FILE_INQ_PADDING = "fileInq_padding";
  
  //Plu Inquiry - D8 - parameters
  private static final String PLU_INQ_DIVISION = "division";
  private static final String PLU_INQ_ITEM_NUMBER = "itemNumber";
  private static final String PLU_INQ_SKU = "SKU";
  private static final String PLU_INQ_TYPE = "pluInquiryType";
  private static final String PLU_INQ_SEGMENT_LEVEL = "segmentLevel";
  
  //Plu Inquiry - D8 - default Values
  private static final String DEFAULT_PLU_INQ_DIVISION = "pluInq_division";
  private static final String DEFAULT_PLU_INQ_ITEM_NUMBER = "pluInq_itemNumber";
  private static final String DEFAULT_PLU_INQ_SKU = "pluInq_sku";
  private static final String DEFAULT_PLU_INQ_TYPE = "pluInq_type";
  private static final String DEFAULT_PLU_INQ_SEGMENT_LEVEL = "pluInq_segmentLevel";
  
  //Coupon Inquiry - 2AA7 - parameters
  private static final String COUPON_INQ_SEGMENT_LEVEL = "segmentLevel";
  private static final String COUPON_INQ_SEGMENT_LENGTH = "segmentLength";
  private static final String COUPON_INQ_COUPON_NUMBER = "couponNumber";
  private static final String COUPON_INQ_STORE_NUMBER = "storeNumber";
  
  //Coupon Inquiry - 2AA7 - default Values
  private static final String DEFAULT_COUPON_INQ_SEGMENT_LEVEL = "couponInq_segmentLevel";
  private static final String DEFAULT_COUPON_INQ_SEGMENT_LENGTH = "couponInq_segmentLength";
  private static final String DEFAULT_COUPON_INQ_COUPON_NUMBER = "couponInq_couponNumber";
  private static final String DEFAULT_COUPON_INQ_STORE_NUMBER = "couponInq_storeNumber";
  
  //Plu Inquiry Response - 98 - parameters
  private static final String PLU_RESP_FUTURE_PURCHASE_COUPON_ID = "futurePurchaseCouponID";
  private static final String PLU_RESP_START_DATE = "startDate";
  private static final String PLU_RESP_END_DATE = "endDate";
  private static final String PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT = "minimumQualificationAmount";
  private static final String PLU_RESP_MAXIMUM_QUANTITY = "maximumQuantity";
  private static final String PLU_RESP_SEARS_CHARGE_FLAG = "searsChargeFlag";
  private static final String PLU_RESP_REGULAR_PRICE_FLAG = "regularPriceFlag";
  private static final String PLU_RESP_PROMOTION_PRICE_FLAG = "promotionPriceFlag";
  private static final String PLU_RESP_CLEARANCE_PRICE_FLAG =  "clearancePriceFlag";
  private static final String PLU_RESP_MALL_FLAG = "mallFlag";
  private static final String PLU_RESP_HARDWARE_FLAG = "hardwareFlag";
  private static final String PLU_RESP_THE_GREAT_INDOORS_FLAG = "theGreatIndoorsFlag";
  private static final String PLU_RESP_AUTOMOTIVE_FLAG = "automotiveFlag";
  private static final String PLU_RESP_OUTLET_FLAG = "outletFlag";
  private static final String PLU_RESP_DEALER_FLAG = "dealerFlag";
  private static final String PLU_RESP_WWW_FLAG =  "wwwFlag";
  private static final String PLU_RESP_PRODUCT_SERVICE_FLAG = "productServiceFlag";
  private static final String PLU_RESP_DELIVERY_FLAG = "deliveryFlag";
  private static final String PLU_RESP_ASSOCIATE_DISCOUNT_FLAG = "associateDiscountFlag";
  private static final String PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE = "reservedforfutureUseOne";
  private static final String PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO = "reservedforfutureUseTwo";
  private static final String PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE = "reservedforfutureUseThree";
  private static final String PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR = "reservedforfutureUseFour";
  private static final String PLU_RESP_COUPON_NUMBER = "couponNumber";
  private static final String PLU_RESP_COUPON_TYPE_CODE = "couponTypeCode";
  private static final String PLU_RESP_MARKDOWN_TYPE_CODE = "markdownTypeCode";
  private static final String PLU_RESP_MARKDOWN_AMOUNT_PERCENT = "markdownAmountPercent";
  private static final String PLU_RESP_FUTURE_PURCHASE_COUPON_DESC = "futurePurchaseCouponDesc";
  
  //Plu Inquiry Response - 98 - default Values
  private static final String DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_ID = "pluResp_98_futurePurchaseCouponID";
  private static final String DEFAULT_PLU_RESP_START_DATE = "pluResp_98_startDate";
  private static final String DEFAULT_PLU_RESP_END_DATE = "pluResp_98_endDate";
  private static final String DEFAULT_PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT = "pluResp_98_minimumQualificationAmount";
  private static final String DEFAULT_PLU_RESP_MAXIMUM_QUANTITY = "pluResp_98_maximumQuantity";
  private static final String DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG = "pluResp_98_searsChargeFlag";
  private static final String DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG = "pluResp_98_regularPriceFlag";
  private static final String DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG = "pluResp_98_promotionPriceFlag";
  private static final String DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG =  "pluResp_98_clearancePriceFlag";
  private static final String DEFAULT_PLU_RESP_MALL_FLAG = "pluResp_98_mallFlag";
  private static final String DEFAULT_PLU_RESP_HARDWARE_FLAG = "pluResp_98_hardwareFlag";
  private static final String DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG = "pluResp_98_theGreatIndoorsFlag";
  private static final String DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG = "pluResp_98_automotiveFlag";
  private static final String DEFAULT_PLU_RESP_OUTLET_FLAG = "pluResp_98_outletFlag";
  private static final String DEFAULT_PLU_RESP_DEALER_FLAG = "pluResp_98_dealerFlag";
  private static final String DEFAULT_PLU_RESP_WWW_FLAG =  "pluResp_98_wwwFlag";
  private static final String DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG = "pluResp_98_productServiceFlag";
  private static final String DEFAULT_PLU_RESP_DELIVERY_FLAG = "pluResp_98_deliveryFlag";
  private static final String DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG = "pluResp_98_associateDiscountFlag";
  private static final String DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE = "pluResp_98_reservedforfutureUseOne";
  private static final String DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO = "pluResp_98_reservedforfutureUseTwo";
  private static final String DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE = "pluResp_98_reservedforfutureUseThree";
  private static final String DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR = "pluResp_98_reservedforfutureUseFour";
  private static final String DEFAULT_PLU_RESP_COUPON_NUMBER = "pluResp_98_couponNumber";
  private static final String DEFAULT_PLU_RESP_COUPON_TYPE_CODE = "pluResp_98_couponTypeCode";
  private static final String DEFAULT_PLU_RESP_MARKDOWN_TYPE_CODE = "pluResp_98_markdownTypeCode";
  private static final String DEFAULT_PLU_RESP_MARKDOWN_AMOUNT_PERCENT = "pluResp_98_markdownAmountPercent";
  private static final String DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_DESC = "pluResp_98_futurePurchaseCouponDesc";
  
  
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
  
  
  
  
  /**
  static{
    try{
      prop = PropertyLoader.loadProperties("default", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }*/
  
  public ScriptCommandParser(String commandLine)
  { 
      try{
        prop = PropertyLoader.loadProperties("params", null);
        PropertyConfigurator.configure(prop);
      }catch(Exception ex){
        logger.error("Error " + ex);
      }
    //System.out.println("Parsing command: [" + commandLine + "]");
    parse(commandLine);
  }
   
  public void parse(String commandLines)
  {  
    
    JsonObjectBuilder builder = new JsonObjectBuilder();
    String[] validIndicators = {"D3", "E3", "D8", "E8", "98", "EA", "2AA7", "2AB7"};
    Stack<String> stack = new Stack<String>();
    String token;
    String key, val;
    StringTokenizer t1;
    
    StringTokenizer tok1 = new StringTokenizer(commandLines, " ");
    this.action = tok1.nextToken();
    StringTokenizer tok2 = new StringTokenizer(tok1.nextToken(),"(");
    this.method = tok2.nextToken();
    
    (t1 = new StringTokenizer(commandLines)).nextToken();
    StringTokenizer tok = new StringTokenizer(t1.nextToken(), "(),=", true);
    
    if(!(method.equals("adjustprice") && action.equals("POST") || action.equals("RECV")))
    {
      boolean found = Boolean.FALSE;
      int startingIndex = commandLines.indexOf("(");
      
        if (startingIndex != -1) 
        {
          int endingIndex = commandLines.indexOf(")", startingIndex);
          if (endingIndex != -1)
          {
            String pattern = commandLines.substring(startingIndex + 1, endingIndex).replace(" ", "");
            for(int i=0; i<validIndicators.length; i++)
            {
              if(pattern.startsWith(validIndicators[i].trim()))
              {   
                  found = Boolean.TRUE;
                  this.parseByteArrayObject(commandLines);
                  break;
              }
            }
            
            if(!found)
              {
              this.parseStringReqRespObject(commandLines, action, method);
              }
            }
         }
      }
    else
    {
    while(tok.hasMoreTokens())
    {
      token = tok.nextToken();
      if(token.equals("(") || token.equals(","))
      {
        String nextToken = tok.nextToken();
        //System.out.println(nextToken);
        if(nextToken.equals(TwilightPojo.ITEMS_KEY))
        {
          builder.newObject(TwilightPojo.ITEMS_KEY);
        }
        else if(nextToken.equals(TwilightPojo.ITEM_KEY))
        {
          builder.newObject(TwilightPojo.ITEM_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.ADJUSTMENTS_KEY))
        {
          builder.newObject(TwilightPojo.ADJUSTMENTS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.DCADJUSTMENTS_KEY))
        {
          builder.newObject(TwilightPojo.DCADJUSTMENTS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.UNUSED_COUPON_CODES))
        {
          builder.newObject(TwilightPojo.UNUSED_COUPON_CODES); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.UNUSED_COUPON_KEY))
        {
          builder.newObject(TwilightPojo.UNUSED_COUPON_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITY_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITY_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_UNAPPLIED_OFFERS_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_UNAPPLIED_OFFERS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_UNAPPLIED_OFFER_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_UNAPPLIED_OFFER_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_REASONS_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_REASONS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_REASON_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_REASON_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(TwilightPojo.INCOMPATIBILITIES_FIELDS_KEY))
        {
          builder.newObject(TwilightPojo.INCOMPATIBILITIES_FIELDS_KEY); 
        }
        else if(nextToken.equals(TwilightPojo.COUPON_KEY))
        {
          builder.newObject(TwilightPojo.COUPON_KEY);    
        }
        else if(nextToken.equals(TwilightPojo.OPTED_PROMO_KEY))
        {
          builder.newObject(TwilightPojo.OPTED_PROMO_KEY);    
        }
        else
        {
          stack.push(nextToken);
        }
      }
      else if(token.equals(")"))
      {
        while(!stack.isEmpty())
        {
          builder.addListItem(stack.pop());
        }
        
        builder.finishObject();
      }
      else if(token.equals("="))
      {
        key = stack.pop();
        val = tok.nextToken();
        builder.addParameter(key, val);
      }
      else if(token.equals("adjustprice"))
      {
        builder.newObject(token);
      }
      else if(isValidResponse(token))
      {
        builder.newObject(token);
      }
      else
      {
        System.err.println("Malformed Script Command...parse error. [" + commandLines + "]");
      }
    }
    this.obj = builder.getJsonObject();
    }
  }
  
  private void parseByteArrayObject(String commandLines)
  {
    StringBuilder sb = new StringBuilder();
    int startingIndex = commandLines.indexOf("(");
      if (startingIndex != -1) 
      {
      int endingIndex = commandLines.indexOf(")", startingIndex);
        if (endingIndex != -1)
        {
          sb.append(commandLines.substring(startingIndex + 1, endingIndex));
        }
          System.out.println("ByteArray Request " + sb);
          this.byteArrayObj = sb;
       }
    }
  
  private boolean isValidResponse(String rsp)
  {
    if(rsp.equals("200") || rsp.equals("400") || rsp.equals("404") || rsp.equals("500"))
      return true;
    else
      return false;
  }
  
  public TwilightJsonObject getJsonObject()
  {
    return this.obj;
  }


  public StringBuilder getByteArrayObject()
  {
    return this.byteArrayObj;
  }
  
  private boolean isToken(String str)
  {
    if(str.equals("(") || str.equals(")") || str.equals(",") || str.equals("="))
      return true;
    else
      return false;
  }
  
  public String getMethod()
  {
    return this.method;
  }
  
  public String getAction()
  {
    return this.action;
  }
  
  public HashMap<String,String> getCartParameters()
  {
    return this.cartParameters;
  }
  
  public List<HashMap> getItemParameters()
  {
    return this.itemParameters;
  }
  
  private void parseStringReqRespObject(String commandLines, String action, String method)
  { 
    StringBuilder sb = new StringBuilder();
    int startingIndex = commandLines.indexOf("(");
    
      if (startingIndex != -1) 
      {  
      int endingIndex = commandLines.indexOf(")", startingIndex);  
        if (endingIndex != -1)
        {
          String cmdStr = commandLines.substring(startingIndex + 1, endingIndex);
          Map<String, String> map = Splitter.on(',').withKeyValueSeparator("=").split(cmdStr);
          
         /**
          * File Inquiry D3
          */
         if(action.equalsIgnoreCase("REQT") && method.equalsIgnoreCase("FILE_INQ") && map.size() > 0)
         {
           String indicator = "D3";
           String fileName = StringUtils.EMPTY;
           String paddedString = StringUtils.EMPTY;
           Map<String, String> modifiableMap = new HashMap<String,String>();
           
           for (Map.Entry<String, String> entry : map.entrySet())
           { 
             if(!map.containsKey(FILE_INQ_FILE_NAME))
             {
                 modifiableMap.put(FILE_INQ_FILE_NAME, prop.getProperty(DEFAULT_FILE_INQ_FILE_NAME));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(FILE_INQ_PADDING))
             {
                 modifiableMap.put(FILE_INQ_PADDING, prop.getProperty(DEFAULT_FILE_INQ_PADDING));
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
               if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_FILE_NAME))
                 fileName = entry.getValue();
               else if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_PADDING) && StringUtils.isNotBlank(fileName))
                 paddedString = StringUtils.rightPad(fileName, Integer.parseInt(entry.getValue().trim()));
             }
           }
           sb.append(indicator).append(" ").append(this.byteResponse(paddedString.getBytes()));
          }
         
         
         /**
          * Plu Inquiry D8
          */
         if(action.equalsIgnoreCase("REQT") && method.equalsIgnoreCase("PLU_INQ") && map.size() > 0)
         {
           String indicator = "D8";
           String division = StringUtils.EMPTY;
           String itemNumber = StringUtils.EMPTY;
           String sku = StringUtils.EMPTY;
           String inqType = StringUtils.EMPTY;
           String segmentLvl = StringUtils.EMPTY;
           Map<String, String> modifiableMap = new HashMap<String,String>();
         
           for (Map.Entry<String, String> entry : map.entrySet())
           { 
             if(!map.containsKey(PLU_INQ_DIVISION))
             {
               modifiableMap.put(PLU_INQ_DIVISION, prop.getProperty(DEFAULT_PLU_INQ_DIVISION));
             } 
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_INQ_ITEM_NUMBER))
             {
               modifiableMap.put(PLU_INQ_ITEM_NUMBER, prop.getProperty(DEFAULT_PLU_INQ_ITEM_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_INQ_SKU))
             {
               modifiableMap.put(PLU_INQ_SKU, prop.getProperty(DEFAULT_PLU_INQ_SKU));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_INQ_TYPE))
             {
               modifiableMap.put(PLU_INQ_TYPE, prop.getProperty(DEFAULT_PLU_INQ_TYPE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_INQ_SEGMENT_LEVEL)) 
             {
               modifiableMap.put(PLU_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_PLU_INQ_SEGMENT_LEVEL));
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
              if(entry.getKey().equalsIgnoreCase(PLU_INQ_DIVISION))
                division = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_INQ_ITEM_NUMBER))
                itemNumber = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_INQ_SKU))
                sku = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_INQ_TYPE))
              {
                inqType= entry.getValue();       
                if(StringUtils.isBlank(inqType))
                {
                  inqType = StringUtils.rightPad(StringUtils.EMPTY, 1);
                }
              }
              else if(entry.getKey().equalsIgnoreCase(PLU_INQ_SEGMENT_LEVEL))
              {
                segmentLvl = entry.getValue();
                if(StringUtils.isNotBlank(segmentLvl))
                  segmentLvl = Integer.toHexString(Integer.parseInt(segmentLvl));
               }
             }
           }
           
           sb.append(indicator)
           .append(" ")
           .append(this.byteResponse(division.getBytes()))
           .append(this.byteResponse(itemNumber.getBytes()))
           .append(this.byteResponse(sku.getBytes()))
           .append(this.byteResponse(inqType.getBytes()))
           .append(segmentLvl)
           .append(" ")
           .append("00");
          }
         
         /**
          * Coupon Inquiry 2AA7
          */
         if(action.equalsIgnoreCase("REQT") && method.equalsIgnoreCase("COUPON_INQ") && map.size() > 0)
         {
           String indicator = "2A A7";
           String segmentLvl = StringUtils.EMPTY;
           String segmentLength = StringUtils.EMPTY;
           String couponNumber = StringUtils.EMPTY;
           String storeNumber = StringUtils.EMPTY;
           
           Map<String, String> modifiableMap = new HashMap<String,String>();
           
           for (Map.Entry<String, String> entry : map.entrySet())
           { 
             if(!map.containsKey(COUPON_INQ_SEGMENT_LEVEL))
             {
                 modifiableMap.put(COUPON_INQ_SEGMENT_LEVEL, prop.getProperty(DEFAULT_COUPON_INQ_SEGMENT_LEVEL));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_INQ_SEGMENT_LENGTH))
             {
                 modifiableMap.put(COUPON_INQ_SEGMENT_LENGTH, prop.getProperty(DEFAULT_COUPON_INQ_SEGMENT_LENGTH));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_INQ_COUPON_NUMBER))
             {
                 modifiableMap.put(COUPON_INQ_COUPON_NUMBER, prop.getProperty(DEFAULT_COUPON_INQ_COUPON_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_INQ_STORE_NUMBER))
             {
                 modifiableMap.put(COUPON_INQ_STORE_NUMBER, prop.getProperty(DEFAULT_COUPON_INQ_STORE_NUMBER));
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
              if(entry.getKey().equalsIgnoreCase(COUPON_INQ_SEGMENT_LEVEL))
                segmentLvl = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_SEGMENT_LENGTH))
              {
                segmentLength = entry.getValue();
                if(StringUtils.isNotBlank(segmentLength))
                  segmentLength = Integer.toHexString(Integer.parseInt(segmentLength));
              }
              else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_COUPON_NUMBER))
                couponNumber = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(COUPON_INQ_STORE_NUMBER))
                storeNumber = entry.getValue();
             }
           }
           
           sb.append(indicator)
           .append(" ")
           .append(segmentLvl.substring(0,2))
           .append(" ")
           .append(segmentLvl.substring(2,4))
           .append(" ")
           .append(segmentLength)
           .append(" ")
           .append("00")
           .append(" ")
           .append(this.byteResponse(couponNumber.getBytes()))
           .append(this.byteResponse(storeNumber.getBytes()));
          }
         
         /**
          * Plu Inquiry Response - 98
          */
         if(action.equalsIgnoreCase("RESP") && method.equalsIgnoreCase("98") && map.size() > 0)
         {
           String indicator = "98";
           String unknown = "02";
           String futurePurchaseCouponID = StringUtils.EMPTY;
           String startDate = StringUtils.EMPTY;
           String endDate = StringUtils.EMPTY;
           String minimumQualificationAmount = StringUtils.EMPTY;
           String maximumQuantity = StringUtils.EMPTY;
           String searsChargeFlag = StringUtils.EMPTY;
           String regularPriceFlag = StringUtils.EMPTY;
           String promotionPriceFlag = StringUtils.EMPTY;
           String clearancePriceFlag =  StringUtils.EMPTY;
           String mallFlag = StringUtils.EMPTY;
           String hardwareFlag = StringUtils.EMPTY;
           String theGreatIndoorsFlag = StringUtils.EMPTY;
           String automotiveFlag = StringUtils.EMPTY;
           String outletFlag = StringUtils.EMPTY;
           String dealerFlag = StringUtils.EMPTY;
           String wwwFlag =  StringUtils.EMPTY;
           String productServiceFlag = StringUtils.EMPTY;
           String deliveryFlag = StringUtils.EMPTY;
           String associateDiscountFlag = StringUtils.EMPTY;
           String reservedforfutureUseOne = StringUtils.EMPTY;
           String reservedforfutureUseTwo = StringUtils.EMPTY;
           String reservedforfutureUseThree = StringUtils.EMPTY;
           String reservedforfutureUseFour = StringUtils.EMPTY;
           String couponNumber = StringUtils.EMPTY;
           String couponTypeCode = StringUtils.EMPTY;
           String markdownTypeCode = StringUtils.EMPTY;
           String markdownAmountPercent = StringUtils.EMPTY;
           String futurePurchaseCouponDesc = StringUtils.EMPTY;
           
           Map<String, String> modifiableMap = new HashMap<String,String>();
           
           for (Map.Entry<String, String> entry : map.entrySet())
           { 
             if(!map.containsKey(PLU_RESP_FUTURE_PURCHASE_COUPON_ID))
             {
                 modifiableMap.put(PLU_RESP_FUTURE_PURCHASE_COUPON_ID, prop.getProperty(DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_ID));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_START_DATE))
             {
                 modifiableMap.put(PLU_RESP_START_DATE, prop.getProperty(DEFAULT_PLU_RESP_START_DATE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_END_DATE))
             {
                 modifiableMap.put(PLU_RESP_END_DATE, prop.getProperty(DEFAULT_PLU_RESP_END_DATE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT))
             {
                 modifiableMap.put(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT, prop.getProperty(DEFAULT_PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             if(!map.containsKey(PLU_RESP_MAXIMUM_QUANTITY))
             {
                 modifiableMap.put(PLU_RESP_MAXIMUM_QUANTITY, prop.getProperty(DEFAULT_PLU_RESP_MAXIMUM_QUANTITY));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_SEARS_CHARGE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_SEARS_CHARGE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_SEARS_CHARGE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_REGULAR_PRICE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_REGULAR_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_REGULAR_PRICE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_PROMOTION_PRICE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_PROMOTION_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PROMOTION_PRICE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             if(!map.containsKey(PLU_RESP_CLEARANCE_PRICE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_CLEARANCE_PRICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_CLEARANCE_PRICE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_MALL_FLAG))
             {
                 modifiableMap.put(PLU_RESP_MALL_FLAG, prop.getProperty(DEFAULT_PLU_RESP_MALL_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_HARDWARE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_HARDWARE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_HARDWARE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_THE_GREAT_INDOORS_FLAG))
             {
                 modifiableMap.put(PLU_RESP_THE_GREAT_INDOORS_FLAG, prop.getProperty(DEFAULT_PLU_RESP_THE_GREAT_INDOORS_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_AUTOMOTIVE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_AUTOMOTIVE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_AUTOMOTIVE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_OUTLET_FLAG))
             {
                 modifiableMap.put(PLU_RESP_OUTLET_FLAG, prop.getProperty(DEFAULT_PLU_RESP_OUTLET_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_DEALER_FLAG))
             {
                 modifiableMap.put(PLU_RESP_DEALER_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DEALER_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_WWW_FLAG))
             {
                 modifiableMap.put(PLU_RESP_WWW_FLAG, prop.getProperty(DEFAULT_PLU_RESP_WWW_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_PRODUCT_SERVICE_FLAG))
             {
                 modifiableMap.put(PLU_RESP_PRODUCT_SERVICE_FLAG, prop.getProperty(DEFAULT_PLU_RESP_PRODUCT_SERVICE_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_DELIVERY_FLAG))
             {
                 modifiableMap.put(PLU_RESP_DELIVERY_FLAG, prop.getProperty(DEFAULT_PLU_RESP_DELIVERY_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG))
             {
                 modifiableMap.put(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG, prop.getProperty(DEFAULT_PLU_RESP_ASSOCIATE_DISCOUNT_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE))
             {
                 modifiableMap.put(PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE, prop.getProperty(DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO))
             {
                 modifiableMap.put(PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO, prop.getProperty(DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE))
             {
                 modifiableMap.put(PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE, prop.getProperty(DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR))
             {
                 modifiableMap.put(PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR, prop.getProperty(DEFAULT_PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_COUPON_NUMBER))
             {
                 modifiableMap.put(PLU_RESP_COUPON_NUMBER, prop.getProperty(DEFAULT_PLU_RESP_COUPON_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_COUPON_TYPE_CODE))
             {
                 modifiableMap.put(PLU_RESP_COUPON_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_COUPON_TYPE_CODE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_MARKDOWN_TYPE_CODE))
             {
                 modifiableMap.put(PLU_RESP_MARKDOWN_TYPE_CODE, prop.getProperty(DEFAULT_PLU_RESP_MARKDOWN_TYPE_CODE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_MARKDOWN_AMOUNT_PERCENT))
             {
                 modifiableMap.put(PLU_RESP_MARKDOWN_AMOUNT_PERCENT, prop.getProperty(DEFAULT_PLU_RESP_MARKDOWN_AMOUNT_PERCENT));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC))
             {
                 modifiableMap.put(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC, prop.getProperty(DEFAULT_PLU_RESP_FUTURE_PURCHASE_COUPON_DESC));
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
              if(entry.getKey().equalsIgnoreCase(PLU_RESP_FUTURE_PURCHASE_COUPON_ID))
                futurePurchaseCouponID = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_START_DATE))
                startDate = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_END_DATE))
                endDate = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MINIMUM_QUALIFICATION_AMOUNT))
                minimumQualificationAmount = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MAXIMUM_QUANTITY))
                maximumQuantity = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_SEARS_CHARGE_FLAG))
                searsChargeFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_REGULAR_PRICE_FLAG))
                regularPriceFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PROMOTION_PRICE_FLAG))
                promotionPriceFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_CLEARANCE_PRICE_FLAG))
                clearancePriceFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MALL_FLAG))
                mallFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_HARDWARE_FLAG))
                hardwareFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_THE_GREAT_INDOORS_FLAG))
                theGreatIndoorsFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_AUTOMOTIVE_FLAG))
                automotiveFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_OUTLET_FLAG))
                outletFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DEALER_FLAG))
                dealerFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_WWW_FLAG))
                wwwFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_PRODUCT_SERVICE_FLAG))
                productServiceFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_DELIVERY_FLAG))
                deliveryFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_ASSOCIATE_DISCOUNT_FLAG))
                associateDiscountFlag = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESERVED_FOR_FUTURE_USE_ONE))
                reservedforfutureUseOne = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESERVED_FOR_FUTURE_USE_TWO))
                reservedforfutureUseTwo = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESERVED_FOR_FUTURE_USE_THREE))
                reservedforfutureUseThree = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_RESERVED_FOR_FUTURE_USE_FOUR))
                reservedforfutureUseFour = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_COUPON_NUMBER))
                couponNumber = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_COUPON_TYPE_CODE))
                couponTypeCode = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MARKDOWN_TYPE_CODE))
                markdownTypeCode = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_MARKDOWN_AMOUNT_PERCENT))
                markdownAmountPercent = entry.getValue();
              else if(entry.getKey().equalsIgnoreCase(PLU_RESP_FUTURE_PURCHASE_COUPON_DESC))
                futurePurchaseCouponDesc = entry.getValue();
             }
           }

           sb.append(indicator)
           .append(" ")
           .append(this.byteResponse(futurePurchaseCouponID.getBytes()))
           .append(unknown)
           .append(this.byteResponse(startDate.getBytes()))
           .append(this.byteResponse(endDate.getBytes()))
           .append(this.byteResponse(minimumQualificationAmount.getBytes()))
           .append(this.byteResponse(maximumQuantity.getBytes()))
           .append(this.byteResponse(searsChargeFlag.getBytes()))
           .append(this.byteResponse(regularPriceFlag.getBytes()))
           .append(this.byteResponse(promotionPriceFlag.getBytes()))
           .append(this.byteResponse(clearancePriceFlag.getBytes()))
           .append(this.byteResponse(mallFlag.getBytes()))
           .append(this.byteResponse(hardwareFlag.getBytes()))
           .append(this.byteResponse(theGreatIndoorsFlag.getBytes()))
           .append(this.byteResponse(automotiveFlag.getBytes()))
           .append(this.byteResponse(outletFlag.getBytes()))
           .append(this.byteResponse(dealerFlag.getBytes()))
           .append(this.byteResponse(wwwFlag.getBytes()))
           .append(this.byteResponse(productServiceFlag.getBytes()))
           .append(this.byteResponse(deliveryFlag.getBytes()))
           .append(this.byteResponse(associateDiscountFlag.getBytes()))
           .append(this.byteResponse(couponNumber.getBytes()))
           .append(this.byteResponse(couponTypeCode.getBytes()))
           .append(this.byteResponse(markdownTypeCode.getBytes()))
           .append(this.byteResponse(markdownAmountPercent.getBytes()))
           .append(this.byteResponse(futurePurchaseCouponDesc.getBytes()));
          }
         
         /**
          * Coupon Inquiry Response - 2AB7
          */
         if(action.equalsIgnoreCase("RESP") && method.equalsIgnoreCase("COUPON_RSP") && map.size() > 0)
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
           
           Map<String, String> modifiableMap = new HashMap<String,String>();
           
           for (Map.Entry<String, String> entry : map.entrySet())
           { 
             if(!map.containsKey(COUPON_RESP_SEGMENT_LENGTH))
             {
                 modifiableMap.put(COUPON_RESP_SEGMENT_LENGTH, prop.getProperty(DEFAULT_COUPON_RESP_SEGMENT_LENGTH));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_SEGMENT_LEVEL))
             {
                 modifiableMap.put(COUPON_RESP_SEGMENT_LEVEL, prop.getProperty(DEFAULT_COUPON_RESP_SEGMENT_LEVEL));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_COUPON_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_COUPON_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_COUPON_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_COUPON_TYPE))
             {
                 modifiableMap.put(COUPON_RESP_COUPON_TYPE, prop.getProperty(DEFAULT_COUPON_RESP_COUPON_TYPE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             if(!map.containsKey(COUPON_RESP_START_DATE))
             {
                 modifiableMap.put(COUPON_RESP_START_DATE, prop.getProperty(DEFAULT_COUPON_RESP_START_DATE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_START_TIME))
             {
                 modifiableMap.put(COUPON_RESP_START_TIME, prop.getProperty(DEFAULT_COUPON_RESP_START_TIME));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_END_DATE))
             {
                 modifiableMap.put(COUPON_RESP_END_DATE, prop.getProperty(DEFAULT_COUPON_RESP_END_DATE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_END_TIME))
             {
                 modifiableMap.put(COUPON_RESP_END_TIME, prop.getProperty(DEFAULT_COUPON_RESP_END_TIME));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             if(!map.containsKey(COUPON_RESP_REDUCTION_TYPE))
             {
                 modifiableMap.put(COUPON_RESP_REDUCTION_TYPE, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_TYPE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_REDUCTION_AMOUNT))
             {
                 modifiableMap.put(COUPON_RESP_REDUCTION_AMOUNT, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_AMOUNT));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_REDUCTION_FLAG))
             {
                 modifiableMap.put(COUPON_RESP_REDUCTION_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_REDUCTION_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_RETAIL_FORMAT))
             {
                 modifiableMap.put(COUPON_RESP_RETAIL_FORMAT, prop.getProperty(DEFAULT_COUPON_RESP_RETAIL_FORMAT));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_COUNT_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_COUNT_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_COUNT_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_ONE_COUPON_PER_TRANSACTION))
             {
                 modifiableMap.put(COUPON_RESP_ONE_COUPON_PER_TRANSACTION, prop.getProperty(DEFAULT_COUPON_RESP_ONE_COUPON_PER_TRANSACTION));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_ONE_COUPON_PER_ITEM))
             {
                 modifiableMap.put(COUPON_RESP_ONE_COUPON_PER_ITEM, prop.getProperty(DEFAULT_COUPON_RESP_ONE_COUPON_PER_ITEM));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_SINGLE_USE_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_SINGLE_USE_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_SINGLE_USE_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INITIAL_PURCHASE_THRESHOLD))
             {
                 modifiableMap.put(COUPON_RESP_INITIAL_PURCHASE_THRESHOLD, prop.getProperty(DEFAULT_COUPON_RESP_INITIAL_PURCHASE_THRESHOLD));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_SIGNAL_ITEM))
             {
                 modifiableMap.put(COUPON_RESP_SIGNAL_ITEM, prop.getProperty(DEFAULT_COUPON_RESP_SIGNAL_ITEM));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_PRICE_MATCH))
             {
                 modifiableMap.put(COUPON_RESP_PRICE_MATCH, prop.getProperty(DEFAULT_COUPON_RESP_PRICE_MATCH));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_GREAT_VALUE))
             {
                 modifiableMap.put(COUPON_RESP_GREAT_VALUE, prop.getProperty(DEFAULT_COUPON_RESP_GREAT_VALUE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_MARKETING_CODE))
             {
                 modifiableMap.put(COUPON_RESP_MARKETING_CODE, prop.getProperty(DEFAULT_COUPON_RESP_MARKETING_CODE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_ITEM_LEVEL_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_ITEM_LEVEL_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_ITEM_LEVEL_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_MANAGER_APPROVAL_FLAG))
             {
                 modifiableMap.put(COUPON_RESP_MANAGER_APPROVAL_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_MANAGER_APPROVAL_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_FRIENDS_AND_FAMILY_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_FRIENDS_AND_FAMILY_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_FRIENDS_AND_FAMILY_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_DISCOUNT_WITH_SEARS_CARD_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING))
             {
                 modifiableMap.put(COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING, prop.getProperty(DEFAULT_COUPON_RESP_VALID_WITH_ZERO_PERCENT_FINANCING));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_REGULAR_PRICE_COUPON))
             {
                 modifiableMap.put(COUPON_RESP_REGULAR_PRICE_COUPON, prop.getProperty(DEFAULT_COUPON_RESP_REGULAR_PRICE_COUPON));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_NBR_OF_INCL_EXCL))
             {
                 modifiableMap.put(COUPON_RESP_NBR_OF_INCL_EXCL, prop.getProperty(DEFAULT_COUPON_RESP_NBR_OF_INCL_EXCL));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_SEQ_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_SEQ_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SEQ_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_FLAG))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_FLAG, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_FLAG));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_DIVISION_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_DIVISION_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_DIVISION_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_LINE_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_LINE_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_LINE_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_NUMBER));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_SUB_LINE_VARIABLE));
             }
             else
             {
               modifiableMap.put(entry.getKey(), entry.getValue());
             }
             
             if(!map.containsKey(COUPON_RESP_INCL_EXCL_ITEM_NUMBER))
             {
                 modifiableMap.put(COUPON_RESP_INCL_EXCL_ITEM_NUMBER, prop.getProperty(DEFAULT_COUPON_RESP_INCL_EXCL_ITEM_NUMBER));
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
                if(StringUtils.isBlank(marketingCode))
                  marketingCode = StringUtils.rightPad(StringUtils.EMPTY, 24);; //Char(24)  Alphanumeric/special character. Leading spaces displayed. 
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
              else if(entry.getKey().equalsIgnoreCase(COUPON_RESP_INCL_EXCL_SEQ_NUMBER))
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
           /**
           if(StringUtils.isNotBlank(nbrOfInclExcl))
           {
             calculatedSegmentLength = Integer.parseInt(segmentLength) + (19 * Integer.parseInt(nbrOfInclExcl));
           }*/
           
           /**
           if (reductionType != null) {
               if (reductionType.equals("2") && Integer.parseInt(reductionAmount) > 10000) 
               {
                     reductionAmount = "00000000";
               }
                 int reductionAmountLength = StringUtils.length(reductionAmount);
                 String wholeNumberReduction = StringUtils.substring(reductionAmount, 0, reductionAmountLength - 2);
                 String fractionReduction =
                         StringUtils.substring(reductionAmount, reductionAmountLength - 2, reductionAmountLength);
                 reductionAmount = wholeNumberReduction + "." + fractionReduction;
             }*/
           
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
           .append(this.byteResponse(nbrOfInclExcl.getBytes()))
           
           .append(this.byteResponse(sequenceNumber.getBytes()))
           .append(this.byteResponse(incExcFlag.getBytes()))
           .append(this.byteResponse(divisionNumber.getBytes()))
           .append(this.byteResponse(lineNumber.getBytes()))
           .append(this.byteResponse(subLineNumber.getBytes()))
           .append(this.byteResponse(subLineVariable.getBytes()));
          }
        } 
      }
      System.out.println("Key/value pair Request " + sb);
      this.byteArrayObj = sb;
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
