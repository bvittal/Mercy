package com.searshc.twilight;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.twilight.parsers.CouponInquiryParser;
import com.searshc.twilight.parsers.CouponResponseParser;
import com.searshc.twilight.parsers.FileInquiryParser;
import com.searshc.twilight.parsers.PluInquiryParser;
import com.searshc.twilight.parsers.PluResponseParser;
import com.searshc.twilight.service.TwilightConstants;
import com.searshc.twilight.util.PropertyLoader;

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
  private String scenario;
  
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
    String[] validIndicators = {
        TwilightConstants.INDICATOR_95,
        TwilightConstants.INDICATOR_98,
        TwilightConstants.INDICATOR_9C,
        TwilightConstants.INDICATOR_D3,
        TwilightConstants.INDICATOR_D8,
        TwilightConstants.INDICATOR_E3,
        TwilightConstants.INDICATOR_E8,
        TwilightConstants.INDICATOR_E9,
        TwilightConstants.INDICATOR_EA,
        TwilightConstants.INDICATOR_EC,
        TwilightConstants.INDICATOR_2AA7,
        TwilightConstants.INDICATOR_2AB7,
        TwilightConstants.INDICATOR_40BA,
        TwilightConstants.INDICATOR_58B1,
        TwilightConstants.INDICATOR_60B1,
        TwilightConstants.INDICATOR_62B1
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
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.COUPON_RESP_INCL_EXCLS_KEY))
        {
          builder.newObject(TwilightPojo.COUPON_RESP_INCL_EXCLS_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.COUPON_RESP_INCL_EXCL_KEY))
        {
          builder.newObject(TwilightPojo.COUPON_RESP_INCL_EXCL_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_EA_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_EA_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_98_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_98_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_E8_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_E8_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_E9_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_E9_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_EC_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_EC_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_95_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_95_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_9C_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_9C_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_40BA_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_40BA_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_60B1_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_60B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_62B1_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_62B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESP_58B1_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESP_58B1_KEY); 
        }
        else if(action.equals("RESP") && nextToken.equals(TwilightPojo.PLU_RESPONSES_KEY))
        {
          builder.newObject(TwilightPojo.PLU_RESPONSES_KEY); 
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
        rsp.equals("500") || rsp.equals(TwilightConstants.REQUEST_INDICATOR_FILE_INQ) || 
        rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ) || rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP) ||
        rsp.equals(TwilightConstants.REQUEST_INDICATOR_COUPON_INQ) || rsp.equals(TwilightConstants.RESPONSE_INDICATOR_COUPON_RESP) ||
        rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I1) || rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I2) ||
        rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I3) || rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I4) || 
        rsp.equals(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I5) || rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R1) ||
        rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R2) || rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R3) ||
        rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R4) || rsp.equals(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R5))
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
  
  private void parseStringReqRespObject(TwilightJsonObject twilightJsonObject)
  { 
    StringBuilder builder = new StringBuilder();
    final String commandType = twilightJsonObject.getName();
    
      /**
      * File Inquiry D3
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_FILE_INQ))
     {
       final FileInquiryParser fileInquiryParser = new FileInquiryParser();
       builder = fileInquiryParser.getFileInquiry(twilightJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       //System.out.println("Key/Value Pair Request - FILE_INQ " + builder);
     }
     
     /**
      * Plu Inquiry D8
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ)
         || commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I1) 
         || commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I2) 
         || commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I3) 
         || commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I4) 
         || commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_PLU_INQ_I5))
     {
       final PluInquiryParser pluInquiryParser = new PluInquiryParser();
       builder = pluInquiryParser.getPluInquiry(twilightJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       //System.out.println("Key/Value Pair Request - PLU_INQ " + builder);
     }
     
     /**
      * Coupon Inquiry 2AA7
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(TwilightConstants.REQUEST_INDICATOR_COUPON_INQ))
     {
       final CouponInquiryParser couponInquiryParser = new CouponInquiryParser();
       builder = couponInquiryParser.getCouponInquiry(twilightJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       //System.out.println("Key/Value Pair Request - COUPON_INQ " + builder);
     }
     
     /**
      * Plu Inquiry Response E8
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP)
         || commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R1)
         || commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R2)
         || commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R3)
         || commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R4)
         || commandType.equalsIgnoreCase(TwilightConstants.RESPONSE_INDICATOR_PLU_RESP_R5))
     {
       final PluResponseParser pluResponseParser = new PluResponseParser();
       builder = pluResponseParser.getPluResponse(twilightJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       //System.out.println("Key/Value Pair Request - PLU_RSP " + builder);
     }
     
     /**
      * Coupon Inquiry Response 2AB7
      */
     if(StringUtils.isNotBlank(commandType) && commandType.equalsIgnoreCase("COUPON_RSP"))
     {
       final CouponResponseParser couponResponseParser = new CouponResponseParser();
       builder = couponResponseParser.getCouponResponse(twilightJsonObject);
       if(builder != null)
       {
         this.byteArrayObj = builder;
       }
       //System.out.println("Key/Value Pair Request - COUPON_RSP " + builder);
     }
   }
 }
