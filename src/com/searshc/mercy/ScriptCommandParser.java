package com.searshc.mercy;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.parsers.CouponInquiryParser;
import com.searshc.mercy.parsers.CouponResponseParser;
import com.searshc.mercy.parsers.CutToCloseOfferInquiryParser;
import com.searshc.mercy.parsers.CutToCloseOfferResponseParser;
import com.searshc.mercy.parsers.DeliveryFeeByZipInquiryParser;
import com.searshc.mercy.parsers.DeliveryFeeByZipResponseParser;
import com.searshc.mercy.parsers.DeliveryPricingInquiryParser;
import com.searshc.mercy.parsers.DeliveryPricingResponseParser;
import com.searshc.mercy.parsers.FileInquiryParser;
import com.searshc.mercy.parsers.InstantDeliveryOfferInquiryParser;
import com.searshc.mercy.parsers.InstantDeliveryOfferResponseParser;
import com.searshc.mercy.parsers.PluInquiryParser;
import com.searshc.mercy.parsers.PluResponseParser;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.PropertyLoader;

public class ScriptCommandParser
{
  private static Properties prop;
  private HashMap<String,String> cartParameters;
  private List<HashMap> itemParameters = new ArrayList<HashMap>();
  private static Logger logger = Logger.getLogger(ScriptCommandParser.class);
  private MercyJsonObject obj;
  private StringBuilder byteArrayObj;
  private String action;
  private String method;
  private String scenario;
  
  public ScriptCommandParser(String commandLine)
  { 
      try{
        prop = PropertyLoader.loadProperties("params", null);
        PropertyConfigurator.configure(prop);
      }catch(Exception ex){
        logger.error("Error " + ex);
      }
    parse(commandLine);
  }

  public void parse(String commandLines)
  {  
    JsonObjectBuilder builder = new JsonObjectBuilder();
    String[] validIndicators = {
        MercyConstants.INDICATOR_95,
        MercyConstants.INDICATOR_98,
        MercyConstants.INDICATOR_9C,
        MercyConstants.INDICATOR_D3,
        MercyConstants.INDICATOR_D8,
        MercyConstants.INDICATOR_E3,
        MercyConstants.INDICATOR_E8,
        MercyConstants.INDICATOR_E9,
        MercyConstants.INDICATOR_EA,
        MercyConstants.INDICATOR_EC,
        MercyConstants.INDICATOR_2AA7,
        MercyConstants.INDICATOR_2AB7,
        MercyConstants.INDICATOR_40BA,
        MercyConstants.INDICATOR_58B1,
        MercyConstants.INDICATOR_60B1,
        MercyConstants.INDICATOR_62B1,

        MercyConstants.INDICATOR_70A4,
        MercyConstants.INDICATOR_70B4,
        MercyConstants.INDICATOR_72A2,
        MercyConstants.INDICATOR_72B2,
        MercyConstants.INDICATOR_11A6,
        MercyConstants.INDICATOR_11B6,
        MercyConstants.INDICATOR_12A1,
        MercyConstants.INDICATOR_12B1,
    };
    
    if(commandLines.contains("#:")){
        this.scenario = commandLines;
    }
    else{
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
    
    boolean found = Boolean.FALSE;
    
    if(!(method.equals("adjustprice") && action.equals("POST") || action.equals("RECV")))
    {
    int startingIndex = commandLines.indexOf("(");
        if (startingIndex != -1){
          int endingIndex = commandLines.indexOf(")", startingIndex);
          if (endingIndex != -1){
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
          }
        }
      }
            
    if(!found)
    {
     while(tok.hasMoreTokens())
     {
      token = tok.nextToken();
        //System.out.println("While loop token " + token);
        if(token.equals("(") || token.equals(","))
        {
          String nextToken = tok.nextToken();
          //System.out.println("nextToken : " + nextToken);
        if(nextToken.equals(MercyPojo.ITEMS_KEY))
        {
          builder.newObject(MercyPojo.ITEMS_KEY);
        }
        else if(nextToken.equals(MercyPojo.ITEM_KEY))
        {
          builder.newObject(MercyPojo.ITEM_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.ADJUSTMENTS_KEY))
        {
          builder.newObject(MercyPojo.ADJUSTMENTS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.ADJUSTMENT_KEY))
        {
          builder.newObject(MercyPojo.ADJUSTMENT_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.DCADJUSTMENTS_KEY))
        {
          builder.newObject(MercyPojo.DCADJUSTMENTS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.DCADJUSTMENT_KEY))
        {
          builder.newObject(MercyPojo.DCADJUSTMENT_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.UNUSED_COUPON_CODES))
        {
          builder.newObject(MercyPojo.UNUSED_COUPON_CODES); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.UNUSED_COUPON_KEY))
        {
          builder.newObject(MercyPojo.UNUSED_COUPON_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITY_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITY_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_UNAPPLIED_OFFERS_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_UNAPPLIED_OFFERS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_UNAPPLIED_OFFER_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_UNAPPLIED_OFFER_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_REASONS_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_REASONS_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_REASON_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_REASON_KEY); 
        }
        else if(action.equals("RECV") && nextToken.equals(MercyPojo.INCOMPATIBILITIES_FIELDS_KEY))
        {
          builder.newObject(MercyPojo.INCOMPATIBILITIES_FIELDS_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.COUPON_RESP_INCL_EXCLS_KEY))
        {
          builder.newObject(MercyPojo.COUPON_RESP_INCL_EXCLS_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.COUPON_RESP_INCL_EXCL_KEY))
        {
          builder.newObject(MercyPojo.COUPON_RESP_INCL_EXCL_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_EA_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_EA_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_98_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_98_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_E8_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_E8_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_E9_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_E9_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_EC_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_EC_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_95_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_95_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_9C_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_9C_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_40BA_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_40BA_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_60B1_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_60B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_62B1_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_62B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESP_58B1_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESP_58B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.COUPON_RESP_2AB7_KEY))
        {
          builder.newObject(MercyPojo.COUPON_RESP_2AB7_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PLU_RESPONSES_KEY))
        {
          builder.newObject(MercyPojo.PLU_RESPONSES_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.PROMO_DESC_KEY))
        {
          builder.newObject(MercyPojo.PROMO_DESC_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(MercyPojo.DESC_KEY))
        {
          builder.newObject(MercyPojo.DESC_KEY); 
        }
        else if(nextToken.equals(MercyPojo.COUPON_KEY))
        {
          builder.newObject(MercyPojo.COUPON_KEY);    
        }
        else if(nextToken.equals(MercyPojo.OPTED_PROMO_KEY))
        {
          builder.newObject(MercyPojo.OPTED_PROMO_KEY);    
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
        if(StringUtils.isBlank(val))
          val = StringUtils.EMPTY;
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
      if(!(action.equalsIgnoreCase("REQT") || action.equalsIgnoreCase("RESP")))
        this.obj = builder.getJsonObject();
      else
        this.parseStringReqRespObject(builder.getJsonObject());
    }
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
          //System.out.println("ByteArray Request " + sb);
          this.byteArrayObj = sb;
       }
    }
  
  private boolean isValidResponse(String rsp)
  {
    if(rsp.equals("200") || rsp.equals("400") || rsp.equals("404") || 
        rsp.equals("500") || rsp.equals(MercyConstants.REQUEST_INDICATOR_FILE_INQ) || 
        rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_COUPON_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_C2C_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_C2C_RESP) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I1) || rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I2) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I3) || rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I4) || 
        rsp.equals(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I5) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R1) ||
        rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R2) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R3) ||
        rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R4) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R5) ||        
        rsp.equals(MercyConstants.REQUEST_INDICATOR_C2C_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_C2C_RESP) || 
        rsp.equals(MercyConstants.REQUEST_INDICATOR_DLVRY_FEE_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_DLVRY_FEE_RESP) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_DLVRY_PRC_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_DLVRY_PRC_RESP) ||
        rsp.equals(MercyConstants.REQUEST_INDICATOR_INST_DLVRY_OFR_INQ) || rsp.equals(MercyConstants.RESPONSE_INDICATOR_INST_DLVRY_OFR_RESP))
      
      return true;
    else
      return false;
  }
  
  public MercyJsonObject getJsonObject()
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
  
  public String getScenario()
  {
    return this.scenario;
  }
  
  public HashMap<String,String> getCartParameters()
  {
    return this.cartParameters;
  }
  
  public List<HashMap> getItemParameters()
  {
    return this.itemParameters;
  }
  
  private void parseStringReqRespObject(MercyJsonObject mercyJsonObject)
  { 
    StringBuilder builder = new StringBuilder();
    final String commandType = mercyJsonObject.getName();
    
      /**
      * File Inquiry D3
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_FILE_INQ))
     {
       final FileInquiryParser fileInquiryParser = new FileInquiryParser();
       builder = fileInquiryParser.getFileInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
     }
     
     /**
      * Plu Inquiry D8
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ)
         || commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I1) 
         || commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I2) 
         || commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I3) 
         || commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I4) 
         || commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_PLU_INQ_I5))
     {
       final PluInquiryParser pluInquiryParser = new PluInquiryParser();
       builder = pluInquiryParser.getPluInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
     }
     
     /**
      * Coupon Inquiry 2AA7
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_COUPON_INQ))
     {
       final CouponInquiryParser couponInquiryParser = new CouponInquiryParser();
       builder = couponInquiryParser.getCouponInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
     }    
     
     /**
      * Plu Inquiry Response E8
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP)
         || commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R1)
         || commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R2)
         || commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R3)
         || commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R4)
         || commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_PLU_RESP_R5))
     {
       final PluResponseParser pluResponseParser = new PluResponseParser();
       builder = pluResponseParser.getPluResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
     }
     
     /**
      * Coupon Inquiry Response 2AB7
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_COUPON_RESP))
     {
       final CouponResponseParser couponResponseParser = new CouponResponseParser();
       builder = couponResponseParser.getCouponResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       
     }
     
     /**
      * Coupon Inquiry 70A4
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_DLVRY_FEE_INQ))
     {
       final DeliveryFeeByZipInquiryParser deliveryFeeByInquiryParser = new DeliveryFeeByZipInquiryParser();
       builder = deliveryFeeByInquiryParser.getDeliveryFeeByZipInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       
     }
     
     /**
      * Coupon Inquiry Response 70B4
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_DLVRY_FEE_RESP))
     {
       final DeliveryFeeByZipResponseParser deliveryFeeByZipResponseParser = new DeliveryFeeByZipResponseParser();
       builder = deliveryFeeByZipResponseParser.getDeliveryFeeByZipResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       
     }
     
     /**
      * Coupon Inquiry 72A2
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_DLVRY_PRC_INQ))
     {
       final DeliveryPricingInquiryParser deliveryPricingInquiryParser = new DeliveryPricingInquiryParser();
       builder = deliveryPricingInquiryParser.getDeliveryPricingInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       
     }
     
     /**
      * Coupon Inquiry Response 72B2
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_DLVRY_PRC_RESP))
     {
       final DeliveryPricingResponseParser deliveryPricingResponseParser = new DeliveryPricingResponseParser();
       builder = deliveryPricingResponseParser.getDeliveryPricingResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       
     }
     
     /**
      * Coupon Inquiry 12A1
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_C2C_INQ))
     {
       final CutToCloseOfferInquiryParser cutToCloseOfferInquiryParser = new CutToCloseOfferInquiryParser();
       builder = cutToCloseOfferInquiryParser.getCutToCloseOfferInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }       
     }
     
     /**
      * Coupon Inquiry Response 12B1          
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_C2C_RESP))
     {
       final CutToCloseOfferResponseParser cutToCloseOfferResponseParser = new CutToCloseOfferResponseParser();
       builder = cutToCloseOfferResponseParser.getCutToCloseOfferResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }      
     }
     
     /**
      * Coupon Inquiry 11A6
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.REQUEST_INDICATOR_INST_DLVRY_OFR_INQ))
     {
       final InstantDeliveryOfferInquiryParser instantDeliveryOfferInquiryParser = new InstantDeliveryOfferInquiryParser();
       builder = instantDeliveryOfferInquiryParser.getInstantDeliveryOfferInquiry(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }       
     }
     
     /**
      * Coupon Inquiry Response 11B6
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(MercyConstants.RESPONSE_INDICATOR_INST_DLVRY_OFR_RESP))
     {
       final InstantDeliveryOfferResponseParser instantDeliveryOfferResponseParser = new InstantDeliveryOfferResponseParser();
       builder = instantDeliveryOfferResponseParser.getInstantDeliveryOfferResponse(mercyJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }  
     }    
     
   }
 }
