package com.searshc.mercy.parsers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.searshc.mercy.MercyJsonObject;
import com.searshc.mercy.service.MercyConstants;
import com.searshc.mercy.util.DecoderUtils;
import com.searshc.mercy.util.PropertyLoader;

public class DeliveryPricingInquiryParser implements LengthCheck
{
  private static Logger logger = Logger.getLogger(DeliveryPricingInquiryParser.class);
  private static Properties prop;
  
  //Delivery Pricing Inquiry - 72A2 - parameters
  private static final String DELIVERY_PRICING_INQ_CLIENT_ID = "clientId";
  private static final String DELIVERY_PRICING_INQ_USER_ID = "userId";
  private static final String DELIVERY_PRICING_INQ_PASSWORD = "password";
  private static final String DELIVERY_PRICING_INQ_SERIVICE_INDICATOR = "serviceIndicator";
  private static final String DELIVERY_PRICING_INQ_ZIP_CODE = "zipCode";
  private static final String DELIVERY_PRICING_INQ_MESSAGE_VERSION = "messageVersion";
  private static final String DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG = "linkedSalescheckFlag";
  private static final String DELIVERY_PRICING_INQ_DELIVERY_DATE = "deliveryDate";
  private static final String DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS = "numberOfItems";
  
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION = "division";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE = "line";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE = "subline";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR = "sublineVar";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER = "itemNumber";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED = "itemSetupOptionSelected";
  private static final String DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY = "quantity";
  
  
  
  
  //Delivery Pricing Inquiry - 72A2 - default Values
  private static final String DEFAULT_DELIVERY_PRICING_INQ_CLIENT_ID = "deliveryPricingInq_72A2_clientId";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_USER_ID = "deliveryPricingInq_72A2_userId";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_PASSWORD = "deliveryPricingInq_72A2_password";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_SERIVICE_INDICATOR = "deliveryPricingInq_72A2_serviceIndicator";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_ZIP_CODE = "deliveryPricingInq_72A2_zipCode";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_MESSAGE_VERSION = "deliveryPricingInq_72A2_messageVersion";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG = "deliveryPricingInq_72A2_linkedSalescheckFlag";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_DELIVERY_DATE = "deliveryPricingInq_72A2_deliveryDate";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS = "deliveryPricingInq_72A2_numberOfItems";
  
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION = "deliveryPricingInq_72A2_division";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE = "deliveryPricingInq_72A2_line";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE = "deliveryPricingInq_72A2_subline";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR = "deliveryPricingInq_72A2_sublineVar";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER = "deliveryPricingInq_72A2_itemNumber";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED = "deliveryPricingInq_72A2_itemSetupOptionSelected";
  private static final String DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY = "deliveryPricingInq_72A2_quantity";
  
  public DeliveryPricingInquiryParser()
  {
    try{
      prop = PropertyLoader.loadProperties("params", null);
      PropertyConfigurator.configure(prop);
    }catch(Exception ex){
      logger.error("Error " + ex);
    }
  }
  
  public StringBuilder getDeliveryPricingInquiry(MercyJsonObject mercyJsonObject){
    StringBuilder builder = new StringBuilder();
    builder = this.processDeliveryPricingInquiry72A2(mercyJsonObject);
    logger.info(builder);
    return builder;
  }
  
  private StringBuilder processDeliveryPricingInquiry72A2(MercyJsonObject mercyJsonObject)
  {
    String indicator = "72 A2";
    String clientId = StringUtils.EMPTY;
    String userId = StringUtils.EMPTY;
    String password = StringUtils.EMPTY;
    String serviceIndicator = StringUtils.EMPTY;
    String zipCode = StringUtils.EMPTY;
    String messageVersion = StringUtils.EMPTY;
    String linkedSalescheckFlag = StringUtils.EMPTY;
    String deliveryDate = StringUtils.EMPTY;
    String numberOfItems = StringUtils.EMPTY;
    
    String division = StringUtils.EMPTY;
    String line = StringUtils.EMPTY;
    String subLine = StringUtils.EMPTY;
    String subLineVar = StringUtils.EMPTY;    
    String itemNumber = StringUtils.EMPTY;
    String quantity = StringUtils.EMPTY;    
    String itemSetupOptionSelected = StringUtils.EMPTY;
    
    final Map<String, String> deliveryPricingInquiryMap = mercyJsonObject.getParameters();
    final Map<String, String> modifiableMap = new HashMap<String,String>();
    final StringBuilder sb = new StringBuilder();
    
  if(deliveryPricingInquiryMap.size() > 0)
  {
    for (Map.Entry<String, String> entry : deliveryPricingInquiryMap.entrySet())
    { 
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_MESSAGE_VERSION))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_MESSAGE_VERSION, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_MESSAGE_VERSION));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_CLIENT_ID))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_CLIENT_ID, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_CLIENT_ID));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_USER_ID))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_USER_ID, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_USER_ID));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_PASSWORD))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_PASSWORD, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_PASSWORD));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_SERIVICE_INDICATOR))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_SERIVICE_INDICATOR, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_SERIVICE_INDICATOR));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_ZIP_CODE))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_ZIP_CODE, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_ZIP_CODE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }     
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_DELIVERY_DATE))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_DELIVERY_DATE, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_DELIVERY_DATE));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
      if(!deliveryPricingInquiryMap.containsKey(DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS))
      {
          modifiableMap.put(DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS));
      }
      else
      {
        modifiableMap.put(entry.getKey(), entry.getValue());
      }
    }
  }
    
    if(modifiableMap.size() > 0)
    {
      for (Map.Entry<String, String> entry : modifiableMap.entrySet())
      { 
       if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_MESSAGE_VERSION))
          messageVersion = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_CLIENT_ID))
         clientId = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_USER_ID))
         userId = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_PASSWORD))
         password = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_SERIVICE_INDICATOR))
         serviceIndicator = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_ZIP_CODE))
         zipCode = entry.getValue();       
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_LINKED_SALESCHECK_FLAG))
         linkedSalescheckFlag = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_DELIVERY_DATE))
         deliveryDate = entry.getValue();
       else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUMBER_OF_ITEMS))
         numberOfItems = entry.getValue();
      }
    }
    
    sb.append(indicator)
    .append(" ")
    .append(this.byteResponse(messageVersion.getBytes())) 
    .append(this.byteResponse(clientId.getBytes()))    
    .append(this.byteResponse(userId.getBytes()))    
    .append(this.byteResponse(password.getBytes()))   
    .append(this.byteResponse(serviceIndicator.getBytes()))   
    .append(this.byteResponse(zipCode.getBytes()))         
    .append(this.byteResponse(linkedSalescheckFlag.getBytes()))   
    .append(this.byteResponse(deliveryDate.getBytes()))   
    .append(this.byteResponse(numberOfItems.getBytes()));  
    
    for(MercyJsonObject numOfItmObj : mercyJsonObject.getMercyJsonObject())
    {
      final Iterator<MercyJsonObject> numOfItmItr = numOfItmObj.getMercyJsonObject().iterator();
      
      while(numOfItmItr.hasNext())
      {
        final Map<String, String> numOfItmMap = numOfItmItr.next().getParameters();
        final Map<String, String> numOfItmModifiableMap = new HashMap<String,String>();
        
        if(numOfItmMap.size() > 0)
        {
          for (Map.Entry<String, String> entry : numOfItmMap.entrySet())
          {
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
              
              if(!numOfItmMap.containsKey(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED))
              {
                numOfItmModifiableMap.put(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED, prop.getProperty(DEFAULT_DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED));
              }
              else
              {
                numOfItmModifiableMap.put(entry.getKey(), entry.getValue());
              }
            }
          }
        
            if(numOfItmModifiableMap.size() > 0)
            {
              for (Map.Entry<String, String> entry : numOfItmModifiableMap.entrySet())
              { 
                 if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_DIVISION)){
                  if(this.lengthCheck(3, entry.getValue()))
                    division = StringUtils.rightPad(entry.getValue(), 3,'0');
                  else
                    System.err.println(lengthCheckMsg(3,entry.getKey()));
                }else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_LINE)){
                  if(this.lengthCheck(3, entry.getValue()))
                    line = StringUtils.rightPad(entry.getValue(), 3,'0');
                  else
                    System.err.println(lengthCheckMsg(3,entry.getKey()));
                }else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE)){
                  if(this.lengthCheck(3, entry.getValue()))
                    subLine = StringUtils.rightPad(entry.getValue(), 3,'0');
                  else
                    System.err.println(lengthCheckMsg(3,entry.getKey()));
               }if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_SUBLINE_VAR)){
                 if(this.lengthCheck(3, entry.getValue()))
                   subLineVar = StringUtils.rightPad(entry.getValue(), 3,'0');
                 else
                   System.err.println(lengthCheckMsg(3,entry.getKey()));
               }else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_NUMBER)){
                 if(this.lengthCheck(5, entry.getValue()))
                   itemNumber = StringUtils.rightPad(entry.getValue(), 5,'0');
                 else
                   System.err.println(lengthCheckMsg(5,entry.getKey()));
               }else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_QUANTITY)){
                 if(this.lengthCheck(5, entry.getValue()))
                   quantity = StringUtils.rightPad(entry.getValue(), 5,'0');
                 else
                   System.err.println(lengthCheckMsg(5,entry.getKey()));
              }else if(entry.getKey().equalsIgnoreCase(DELIVERY_PRICING_INQ_NUM_OF_ITMS_ITEM_SETUP_OPTION_SELECTED)){
                if(this.lengthCheck(1, entry.getValue()))
                  itemSetupOptionSelected = StringUtils.rightPad(entry.getValue(), 1,'0');
                else
                  System.err.println(lengthCheckMsg(1,entry.getKey()));
             }              
            
                }
              }
          
          sb.append(this.byteResponse(division.getBytes()))
            .append(this.byteResponse(line.getBytes()))
            .append(this.byteResponse(subLine.getBytes()))
            .append(this.byteResponse(subLineVar.getBytes()))
            .append(this.byteResponse(itemNumber.getBytes()))
            .append(this.byteResponse(quantity.getBytes()))
            .append(this.byteResponse(itemSetupOptionSelected.getBytes()));
            }
          }
    if(DecoderUtils.lengthMatch(MercyConstants.INDICATOR_72A2, sb)){
      return sb;
    }
    return null;
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

  @Override
  public boolean lengthCheck(int length, String field)
  {
   if(!(StringUtils.isNotBlank(field) && field.length() > length)){
     return Boolean.TRUE;
   }
    return Boolean.FALSE;
  }

  @Override
  public String lengthCheckMsg(int length, String field)
  {
    return "Indicator : " + MercyConstants.INDICATOR_72A2+ " length check failed for : " + field + " field, required " + length +" char(s)";
  }
}

  