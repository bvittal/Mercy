package com.searshc.twilight;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
           
           for (Map.Entry<String, String> entry : modifiableMap.entrySet())
           { 
             if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_FILE_NAME))
               fileName = entry.getValue();
             else if(entry.getKey().trim().equalsIgnoreCase(FILE_INQ_PADDING) && StringUtils.isNotBlank(fileName))
               paddedString = StringUtils.rightPad(fileName, Integer.parseInt(entry.getValue().trim()));
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
              if(StringUtils.isBlank(inqType) || inqType.equalsIgnoreCase("null"))
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
          * File Inquiry 2AA7
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
